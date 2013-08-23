window.NewMovieFormView = Backbone.View.extend({
	
	events: {
		"click #submitForm": "submitForm",
		"click #cancelButton": "cancelForm"
	},
	
	initialize: function() {
		this.statusList = StatusCollection;
		this.typeList = TypeCollection;
		$("#alerts").hide();
	},
	
    render: function() {
        $(this.el).html(this.template({model: this.model, movieFormTitle: this.options.title}));
        
        $('#datepicker', this.el).datepicker({
        	format: " yyyy",
        	viewMode: "years",
        	minViewMode: "years",
        });
        
        _.each(this.statusList.models, function(status) {
			$('#selectStatus', this.el).append(new SelectListView({model:status, optionParam: "option"}).render().el);
		}, this);
        
        _.each(this.typeList.models, function(type) {
			$('#selectType', this.el).append(new SelectListView({model:type, optionParam: "option"}).render().el);
		}, this);
        
        var genreCollection = new GenreCollection();
        var that = this;
    	genreCollection.fetch({
    		success: function(data) {
    			_.each(data.models, function(genre) {
    				$('#selectGenre', this.el).append(new SelectListView({model:genre, optionParam: "name"}).render().el);
    			}, this);
    			if (!that.model.isNew()) {
    				that.populateExistingValues();
    			}
    		}
    	});
        
        return this;
    },
    
    populateExistingValues: function() {
    	document.getElementById("name").value = this.model.get("name");
    	document.getElementById("year").value = this.model.get("releaseYear");
    	document.getElementById("selectStatus").value = this.model.get("status");
    	document.getElementById("selectType").value = this.model.get("type");
    	document.getElementById("selectGenre").value = this.model.get("genreName");
    },
    
    submitForm: function() {
    	var name = $("#name").val();
    	var year = $("#year").val().trim();
    	var status = $("#selectStatus").val();
    	var type = $("#selectType").val();
    	var genre = $("#selectGenre").val();
    	
    	if ((name == "") || (year == "") || (status == "Select Status ...") || (type == "Select Type ...") 
    			|| (genre == "Select Genre ...")) {
    		$("#alerts").addClass("alert-danger").html(_.template("<%= alertMessage %>", 
    				{alertMessage:"Fill in all fields!"})).show();
    	}
    	else {
    		this.model.set({
    			"name": name,
    			"releaseYear": year,
    			"status": status,
    			"type": type,
    			"genreName": genre
    		});
    		if (this.model.isNew()) {
    			this.collection = new MoviesCollection();
    			this.collection.fetch();
    			this.collection.create(this.model, {
    				success: function() {
    					app.navigate("movies", {trigger: true});
    					$("#alerts").addClass("alert-success").html(_.template("<%= alertMessage %>", 
    		    				{alertMessage:"Successfully created new entry: " + name + "!"})).show();
    				},
    				failure: function() {
    					$("#alerts").addClass("alert-danger").html(_.template("<%= alertMessage %>", 
    		    				{alertMessage:"ERROR: Failed to create entry: " + name + "!"})).show();
    				}
    			});
    		} else {
    			this.model.save();
    			app.movieDetails(this.model.id);
    		}
    	}
    },
    
    cancelForm: function() {
    	if (this.options.title == "Edit Movie") {
    		app.movieDetails(this.model.id);
    	} else if (this.options.title == "Add New Movie") {
    		app.navigate("movies", {trigger: true});
    	}
    }
	
});

window.SelectListView = Backbone.View.extend({
	
	tagName: "option",
	
	initialize: function() {
		this.optionParam = this.options.optionParam;
	},
	
	render: function() {
		$(this.el).attr('value', this.model.get(this.optionParam))
			.html(_.template("<%=option%>", {option:this.model.get(this.optionParam)}));
	    return this;
	  }
	
});


