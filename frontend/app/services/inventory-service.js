import Ember from 'ember';
import BaseService from './base-service';
import Inventory from '../models/inventory';

export default BaseService.extend({

  // admin
  all() {
    var inventories = [];
console.log("ajla1");
    this.ajax({ url: "equipment_type/all", type: "GET" }).then(function(data) {
      console.log(data);
      data.forEach(function(inventory) {
        inventories.addObject(Inventory.create(inventory));
      });
    });
    return inventories;
  },

  filterByTerm(term) {
    var inventories = [];
    this.ajax({ url: `equipment_type/filter/${term}`, type: "GET" }).then(function(data) {
      data.forEach(function(inventory) {
        inventories.addObject(Inventory.create(inventory));
      });
    });
    return inventories;
  },

  getById(id) {
    var inventory = Inventory.create({});
    this.ajax({ url: `equipment_type/${id}`, type: "GET"}).then(function(data) {
      inventory.setProperties(data);
    });

    return inventory;
  },

  edit(inventoryId, data) {
    return this.ajax({ url: `equipment_type/${inventoryIdId}`, type: "PUT", data: JSON.stringify(data) });
  },

  create(data) {
    return this.ajax({ url: `equipment_type`, type: "POST", data: JSON.stringify(data)})
  },

  delete(inventoryId) {
    return this.ajax({ url: `equipment_type/${inventoryId}`, type: "DELETE"});
  },
});
