var passport = require('passport');
var LocalStrategy = require('passport-local').Strategy;
var User = require('mongoose').model('User');

module.exports = function () {
	passport.use(
		new LocalStrategy(
				function(username, password, done) {
					var uName = {username: username};
					User.findOne(uName, function (err, user) {
							
							if(err) {
								return done(err);
							}			

							if(!user) {
								var msgDict = {message: 'Unknown User'};
								return done(null, false. msgDict);
							}

							if(!user.authenticate(password)) {
								var msgDict = {message: 'Invalid Password'};
								return done(null, false, msgDict);								
							}

							return done(null, user);

						}
					);
				}
			)
		);
};