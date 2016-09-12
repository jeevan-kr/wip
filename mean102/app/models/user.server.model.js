var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var crypto = require('crypto');

var UserSchema = new Schema({
		firstName:String,
		lastName:String,
		email:{
			type: String,
			//index: true, //reconsider in production
			match: [/.+\@.+\..+/, "Please fill a valid e-mail address"]
		},
		
		username:{ 
			type: String, 
			trim: true,
			unique: true,
			required:'Username is required'
		},
		
		password:{
			type:String,
			validate: [
				function (password) {
					return password && password.length > 6;
				},
				'Password should be longer'
			]
		},

		salt: {
			type: String
		},
		
		provider:{
			type: String,
			required: 'Provider is required'
		},
		
		providerId: {type:String},

		providerData : {

		},

		role:{
			type:String,
			enum:['Admin', 'Owner', 'User']
		},

		created: {
			type:Date,
			default:Date.now
		},

		website: {
			type:String,
			/*
			set: function(url) {
				if(!url) {
					return url;
				} else {
					if(url.indexOf('http://') !== 0 
						&& url.indexOf('https://') !== 0) {

						url = 'http://' + url;
					}
				}
				return url;
			},
			*/
			get:function(url) {
				if(!url) {
					return url;
				} else {
					if(url.indexOf('http://') !== 0 
						&& url.indexOf('https://') !== 0) {

						url = 'http://' + url;
					}
				}
				return url;
			}
		}
	});


UserSchema.virtual('fullName').get( function(){
	return this.firstName + ' ' + this.lastName;
}).set(function(fullName) {
		var splitName = fullName.split(' ');
		this.firstName = splitName[0] || '';
		this.lastName = splitName[1] || '';
	}
);

UserSchema.pre('save', function(next) {
	if (this.password) {
		this.salt = new
		Buffer(crypto.randomBytes(16).toString('base64'), 'base64');
		this.password = this.hashPassword(this.password);
	}
	next();
});

UserSchema.methods.hashPassword = function(password) {
	return crypto.pbkdf2Sync(password, this.salt, 10000,64).toString('base64');
};

UserSchema.methods.authenticate = function(password) {
	return this.password === this.hashPassword(password);
};

UserSchema.statics.findUniqueUsername = function(username, suffix,callback) {
	var _this = this;
	var possibleUsername = username + (suffix || '');
	_this.findOne({username: possibleUsername}, function(err, user) {
		if (!err) {
			if (!user) {
				callback(possibleUsername);
			} else {
				return _this.findUniqueUsername(username, (suffix || 0) +1, callback);
			}
		} else {
			callback(null);
		}
	});
};

UserSchema.post('save', function(next){
	if(this.isNew) {
		console.log('A new user was created.');
	} else {
		console.log('A user update is details.');
	}
});
UserSchema.set('toJSON', {getters:true, virtuals:true});

mongoose.model('User',UserSchema);

//Posts Schema

var PostSchema = new Schema({
		title: {
			type:String,
			required: true
		},

		content: {
			type:String,
			required: true
		},

		author: {
			type: Schema.ObjectId,
			ref:'User'
		}
	}
);

mongoose.model('Post', PostSchema);
/*
	curl -X POST -H "Content-Type:application/json" -d '{"firstName":"First", "lastName":"Last","email": "user@example.com","username":"username","password":"password"}' localhost:3000/users
	//Use POSTMAN the above command doesn't work in Windows
*/