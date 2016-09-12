exports.render = function(req,res) {
	
	/*if(req.session.lastVisit) {
		console.log(req.session.lastVisit);
	}

	req.session.lastVisit = new Date();
	*/
	var vars = {
		title: 'Hello, World - Jeevan',
		user: JSON.stringify (req.user)
	};
	res.render('index', vars);
	/*
	res.render('index',{
		title:'Jeevan App',
		userFullName:req.user? req.user.fullName:''
	});
*/

};