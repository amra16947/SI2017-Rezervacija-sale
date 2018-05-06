import Ember from 'ember';
import BaseService from './base-service';
import Equipment from '../models/equipment';

export default BaseService.extend({
    // admin    
    all() {
        var types = [];
        this.ajax({ url: "classroom-equipment/all", type: "GET" }).then(function(data) {
            data.forEach(function(equip) {
                types.addObject(Equipment.create(equip));
            });
        });    
        return types;
    },

    create(data) {
        return this.ajax({ url: `classroom-equipment`, type: "POST", data: JSON.stringify(data)})
    },

    delete(id) {
        return this.ajax({ url: `classroom-equipment/${id}`, type: "DELETE"});
    },
});
