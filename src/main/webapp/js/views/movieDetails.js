window.MovieView = Backbone.View.extend({
	
	initialize: function() {
		$("#alerts").hide();
	}, 
	
	render: function() {
	    $(this.el).html(this.template(this.model.toJSON()));
	    return this;
	  }
});