window.Genre = Backbone.Model.extend({
	
	urlRoot: "./api/movies/genres",
	
	defaults: {
		"id": null,
		"name": ""
	}
});

window.GenreCollection = Backbone.Collection.extend({
	
	model: Genre,
	url: "./api/movies/genres"
	
});