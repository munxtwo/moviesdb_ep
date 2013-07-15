window.NewMovieFormView = Backbone.View.extend({
	
	events: {
		"click #submitForm": "submitForm",
	},
	
	initialize: function() {
		this.statusList = StatusCollection;
		this.typeList = TypeCollection;
	},
	
    render: function() {
        $(this.el).html(this.template({model: this.model}));
        
        $('#datepicker', this.el).datepicker({
        	format: " yyyy",
        	viewMode: "years",
        	minViewMode: "years",
        });
        
        _.each(this.statusList.models, function(status) {
			$('#selectStatus', this.el).append(new SelectListView({model:status}).render().el);
		}, this);
        
        _.each(this.typeList.models, function(type) {
			$('#selectType', this.el).append(new SelectListView({model:type}).render().el);
		}, this);
        
        return this;
    },
    
    submitForm: function() {
    	var name = $("#name").val();
    	var year = $("#year").val();
    	var status = $("#selectStatus").val();
    	var type = $("#selectType").val();
    	
    	if ((name == "") || (year == "") || (status == "Select Status ...") || (type == "Select Type ...")) {
    		alert("Fill in all fields!");
    	}
    	else {
    		this.model.set({
    			"name": name,
    			"releaseYear": year,
    			"status": status,
    			"type": type,
    		});
//    		alert(this.model.get("name") + ", " + this.model.get("releaseYear") + ", " + this.model.get("status") + ", " + this.model.get("type"));
    	}
    	if (this.model.isNew()) {
    		this.collection = new MoviesCollection();
    		this.collection.fetch();
    		this.collection.create(this.model);
    	}
    },
	
});

window.SelectListView = Backbone.View.extend({
	
	tagName: "option",
	
	render: function() {
		$(this.el).attr('value', this.model.get("option"))
			.html(_.template("<%=option%>", {option:this.model.get("option")}));
	    return this;
	  }
	
});


