window.HomeView = Backbone.View.extend({

	initialize: function() {
		$("#alerts").hide();
	}, 
	
    render: function () {
        $(this.el).html(this.template());
//        $('.navbar-search', this.el).append(this.searchresultsView.render().el);
        return this;
    },

});