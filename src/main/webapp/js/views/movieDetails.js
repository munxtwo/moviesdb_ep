window.MovieView = Backbone.View.extend({
	
	initialize: function() {
		$("#alerts").hide();
	}, 
	
	events: {
		"click #editEntry": "editEntry",
		"click #deleteEntry": "deleteEntry"
	},
	
	render: function() {
	    $(this.el).html(this.template(this.model.toJSON()));
	    return this;
	},
	
	editEntry: function() {
		this.editEntry = new NewMovieFormView({model: this.model, title: "Edit Movie"});
		$("#content").html(this.editEntry.render().el);
	},
	
	deleteEntry: function() {
		var name = this.model.get("name");
		this.model.destroy({
			success: function() {
				app.navigate("movies", {trigger: true});
				$("#alerts").addClass("alert-success").html(_.template("<%= alertMessage %>", 
	    				{alertMessage:"Successfully deleted entry: " + name + "!"})).show();
			}
		});
	}
});