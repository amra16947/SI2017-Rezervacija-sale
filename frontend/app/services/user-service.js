import Ember from 'ember';
import BaseService from './base-service';
import User from '../models/user';

export default BaseService.extend({
	usernameExists: function(username) {
    	return this.ajax({ url: `users/exists/${username}`, type: "GET" });
    },

    // admin    
    all() {
        var users = [];
        this.ajax({ url: "users/all", type: "GET" }).then(function(data) {
            data.forEach(function(user) {
                users.addObject(User.create(user));
            });
        });    
        return users;
    },

    filterByTerm(term) {
        var users = [];
        this.ajax({ url: `users/filter/${term}`, type: "GET" }).then(function(data) {
            data.forEach(function(user) {
                users.addObject(User.create(user));
            });
        });    
        return users;
    },

    getById(id) {
        var user = User.create({});
        this.ajax({ url: `users/${id}`, type: "GET"}).then(function(data) {
            user.setProperties(data);
        });        

        return user;
    },

    edit(userId, data) {
        return this.ajax({ url: `users/${userId}`, type: "PUT", data: JSON.stringify(data) });
    },

    create(data) {
        return this.ajax({ url: `users`, type: "POST", data: JSON.stringify(data)})
    },

    delete(userId) {
        return this.ajax({ url: `users/${userId}`, type: "DELETE"});
    },
});
