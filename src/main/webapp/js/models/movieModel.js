window.MoviesListHeaders = Backbone.Model.extend({
	
	initialize: function() {
		this.set({columnNames: ["#",
	              "Name",
	              "Year",
	              "Status",
	              "Type",
	              "Genre"
		]});
	}
});

window.Movie = Backbone.Model.extend({
	
	urlRoot: "./api/movies",
	
	defaults: {
		"id": null,
		"name": "",
		"releaseYear": "",
		"status": "",
		"type": "",
		"genre": ""
	}
});

window.MoviesCollection = Backbone.Collection.extend({
	
	initialize: function(options) {
		this.baseUrl = "./api/movies/";
		if (typeof options === "undefined") {
			this.url = this.baseUrl;
		} 
		else {
			this.url = this.baseUrl + options.url;
		}
	},
	
	model: Movie,
	
	url: function () {
		return this.url;
	}
	
});
