var passport = require('passport');
var mongoose = require('mongoose');
module.exports = function() {
	var User = mongoose.model('User');

	passport.serializeUser(function(user,done){
		done(null, user.id);
	});

	passport.deserializeUser(function(id,done) {
		var idDict = {_id:id};
		var tempStr = '-password -salt'
		User.findOne(idDict, tempStr, function(err, user) {
				done(err, user);
			}
		);
	});

	require('./strategies/local.js')();
	require('./strategies/facebook.js')();
	require('./strategies/google.js')();

};