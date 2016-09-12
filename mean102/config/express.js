//Instantiate the key modules
var express = require('express');
var morgan = require('morgan');
var compress = require('compression');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var session = require('express-session');
var passport = require('passport');
var flash = require('connect-flash');

//Link the local config folder
var config = require('./config');


module.exports = function() {

	//Create an App instance
	var app = express();
	
	//var mongoose = require('../config/mongoose');

	if(process.env.NODE_ENV === 'development') {


		app.use(morgan('dev'));
	} else if (process.env.NODE_ENV === 'production') {
		app.use(compress());
	}
	
	var opt = {extended:true}

	app.use(bodyParser.urlencoded(opt));
	app.use(bodyParser.json());
	app.use(methodOverride());

	var sessionVars = {
		saveUninitialized:true,
		resave:true,
		secret:config.sessionSecret
	};

	app.use(session(sessionVars));

	app.set('views','./app/views');
	app.set('view engine', 'ejs');

	app.use(flash());
	app.use(passport.initialize());
	app.use(passport.session());

	require('../app/routes/index.server.routes.js') (app);
	require('../app/routes/users.server.routes.js') (app);
	
	app.use(express.static('./public'));
	return app;
};