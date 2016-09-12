//File name: config/mongoose.js

var config = require('./config');
var mongoose = require('mongoose');
module.exports = function() {

	var db = mongoose.connect(config.dbURI);
	require('../app/models/user.server.model');
	return db;	
};