window.MoviesListView = Backbone.View.extend({
	
	tagName: "div",
	
	className: "row",

	events: {
		"click .year": "filterByYear",
		"click .all": "render",
		"click #newEntry": "createNewEntry",
	},

	initialize: function() {
		this.columnHeaders = new MoviesListHeaders();
		this.title = this.options.title;
		var alerts = document.getElementById("alerts");
		if (alerts.className.indexOf("alert-success") == -1) {
			$("#alerts").hide();
		}
	},
	
	render: function() {
		$(this.el).html(this.template({model:this.model, sidebar:this.options.sidebar, years:this.options.years, title:this.title}));
		
		_.each(this.options.years, function(year) {
			$('#years', this.el).append(new YearListView({model:year}).render().el);
		}, this);

		$('#columnHeaders', this.el).append(new MoviesListHeaderView({model:this.columnHeaders}).render().el);
		
		_.each(this.model.models, function(movie) {
			$('#columnBody', this.el).append(new MoviesListItemView({model:movie}).render().el);
		}, this);
		
	    return this;
	},
	  
	filterByYear: function() {
		var id = arguments[0].currentTarget.id;
		this.title = "List of Movies in " + id; 
		$("h1").html(this.title);
		
		$('.nav-pills li').removeClass('active');
        $('.' + id).addClass('active');

        var filtered = this.collection.filter(function(movie) {
        	return movie.get("releaseYear") == id;
        });
        
        $('#columnBody', this.el).empty();
        _.each(filtered, function(movie) {
        	$('#columnBody', this.el).append(new MoviesListItemView({model:movie}).render().el);
        }, this);
        
	},
	
	createNewEntry: function() {
		app.navigate("movies/create/newEntry", {trigger: true});
	},

});

window.MoviesListHeaderView = Backbone.View.extend({
	
	tagName: "tr",
	
	render: function() {
		_.each(this.model.get("columnNames"), function(columnNames) {
			$(this.el).append(new MoviesListHeaderItemView({model:columnNames}).render().el);
		}, this);
	    return this;
	  }
	
});

window.MoviesListHeaderItemView = Backbone.View.extend({
	
	tagName: "th",
	
	render: function() {
	    $(this.el).html(this.template({columnNames:this.model}));
	    return this;
	  }
});

window.MoviesListItemView = Backbone.View.extend({
	
	tagName: "tr",
	
	render: function() {
	    $(this.el).html(this.template(this.model.toJSON()));
	    return this;
	  }
	
});

window.YearListView = Backbone.View.extend({

	tagName: "li",

	initialize: function() {
		this.year = this.model;
	},

	render: function() {
		$(this.el).attr('id', '' + this.year).addClass('year ' + this.year).html(_.template("<a href='#movies'><%=year%></a>", {year:this.model}));
		return this;
	}
});