// File Name: app/controllers/users.server.controller.js
//continue with pg 137
var mongoose = require('mongoose');
var User = mongoose.model('User');

var getErrorMessage = function(err) {
	var message = '';

	if(err.code) {
		switch(err.code) {
			case 11000:
			case 11001:
				message = 'Username already exists';
				break;
			default:
				message = 'Something went wrong';
		}
	} else {
		for(var errName in err.errors) {
			if(err.errors[errName].message ) {
				message = err.errors[errName].message;
			}
		}
	}

	return message;
};

exports.renderSignIn = function(req,res,next) {
	if(!req.user) {
		var pageDict = {
			title:'Sign-in Form',
			messages: req.flash('error') || req.flash('info')
		};
		
		res.render('signin', pageDict);
	} else {
		return res.redirect('/');
	}
};

exports.renderSignup = function(req,res,next) {
	if(!req.user) {
		var pageDict = {
			title:'Sign-up Form',
			messages: req.flash('error')
		};
		
		res.render('signup', pageDict);

	} else {
		return res.redirect('/');
	}
};

exports.signup = function(req,res,next) {
	if(!req.user) {
		var user = new User(req.body);
		var message = null;

		user.provider = 'local';

		user.save(function(err){
			if(err) {
				var message = getErrorMessage(err);
				req.flash('error',message);
				return res.redirect('/signup');
			}

			req.login(user, function(err) {
				if(err) {
					return next(err);
				} 

				return res.redirect('/');
			});
		});
	} else {
		return res.redirect('/');
	}
};

exports.signout = function (req,res) {
	req.logout();
	res.redirect('/');
};

exports.saveOAuthUserProfile = function (req, profile, done) {
	var provDict = {
		provider: profile.provider,
		providerId: profile.providerId
	};
	User.findOne(provDict, function (err, user){
		if(err) {
			return done(err);
		} else {
			if(!user) {
				var possibleUsername = profile.username || 
					((profile.email)? profile.email.split('@')[0]:'');
				var funct = function (availableUsername) {
					profile.username = availableUsername;

					user = new User(profile);
					user.save(function(err) {
						if(err) {
							var message = _this.getErrorMessage(err);

							req.flash('error', message);
							return res.redirect('/signup');
						}

						return done(err, user);
					});
				};

				User.findUniqueUsername(possibleUsername, null, funct);
			} else {
				return done(err, user);
			}
		}
	});
};
/*
exports.create = function (req,res,next) {
	var user = new User(req.body);

	user.save(function(err) {
			if(err) {
				return next(err);
			} else {
				res.json(user);
			}
		}
	);
};

exports.list = function (req,res,next) {
	User.find({}, function(err, users) {
			if(err) {
				return next(err);
			} else {
				res.json(users);
			}
		}
	);
}

exports.read = function(req, res) {
	res.json(req.user);
};

exports.userByID = function(req, res, next, id) {
	var idDict = {_id:id};

	User.findOne(idDict, function(err, user) {
			if (err) {
				return next(err);
			} else {
				req.user = user;
				next();
			}
		}
	);
};

exports.update = function (req,res,next) {
	User.findByIdAndUpdate(req.user.id, req.body, function (err,user) {
			if(err) {
				return(next(err));
			} else {
				res.json(user);
			}
		}

	);
};

exports.delete = function(req,res,next) {
	req.user.remove(function(err) {
			if(err) {
				return next(err);
			} else {
				res.json(req.user);
			}
		}
	);
};*/