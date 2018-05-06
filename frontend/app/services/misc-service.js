import Ember from 'ember';
import BaseService from './base-service';
import Stats from '../models/stats';

export default BaseService.extend({
    stats(id) {
        var stats = Stats.create({});
        this.ajax({ url: `stats`, type: "GET"}).then(function(data) {
            stats.setProperties(data);
        });        

        return stats;
    }
});
