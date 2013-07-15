var appRouter = Backbone.Router.extend({
	
	routes: {
		"": "home",
		"movies": "allmovies",
		"movies/:id": "movieDetails",
		"movies/create/newEntry": "newEntry",
	},
	
	initialize: function () {
        this.headerView = new HeaderView();
        $('.header').html(this.headerView.render().el);
        $('.carousel').carousel({
        	  interval: 2000
    	});
        this.title = "";
    },
    
    home: function () {
        // Since the home view never changes, we instantiate it and render it only once
        if (!this.homeView) {
            this.homeView = new HomeView();
            this.homeView.render();
        } else {
            this.homeView.delegateEvents(); // delegate events when the view is recycled
        }
        $("#content").html(this.homeView.el);
        this.headerView.select('home-menu');
        $('.carousel').carousel('cycle');
        
        var upcomingMovies = new MoviesCollection({url: "status/Upcoming"});
        var that = this;
        upcomingMovies.fetch({
        	success: function(data) {
        		that.title = "List Of Upcoming Movies";
                $("#content", this.el).append(new MoviesListView({model: data, sidebar: "noshow", title: that.title}).render().el);
    		}
        });

    },
    
    allmovies: function() {
    	var collection = new MoviesCollection();
    	var that = this;
    	collection.fetch({
    		success: function(data) {
                var years = _.uniq(collection.pluck("releaseYear"));
                that.title = "List Of Movies";
    			$("#content").html(new MoviesListView({model: data, collection: collection, 
    				sidebar: "show", years: years, title: that.title}).render().el);
    		}
    	});

    	this.headerView.select('all-movies-menu');
    },
    
    movieDetails: function(id) {
    	 var movie = new Movie({id: id});
         movie.fetch({
             success: function (data) {
                 $('#content').html(new MovieView({model: data}).render().el);
             }
         });
    },
    
    newEntry: function() {
    	$("#content").html(new NewMovieFormView({model: new Movie()}).render().el);
        this.headerView.select('');
    },
    
});

templateLoader.load(["HomeView", "HeaderView", "MoviesListView", "MoviesListHeaderItemView", "MoviesListItemView", 
                     "MovieView", "NewMovieFormView"],
	    function () {
	        app = new appRouter();
	        Backbone.history.start();
	    });
