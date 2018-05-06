import Ember from 'ember';
import BaseService from './base-service';
import Access from '../models/access';

export default BaseService.extend({
    // admin    
    all() {
        var grants = [];
        this.ajax({ url: "access-grants/all", type: "GET" }).then(function(data) {
            data.forEach(function(grant) {
                grants.addObject(Access.create(grant));
            });
        });    
        return grants;
    },

    create(data) {
        return this.ajax({ url: `access-grants`, type: "POST", data: JSON.stringify(data)})
    },

    delete(id) {
        return this.ajax({ url: `access-grants/${id}`, type: "DELETE"});
    },
});
