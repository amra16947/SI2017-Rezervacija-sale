import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('login');
  this.route('users', function() {});
  this.route('classrooms');
  this.route('inventory', {path: "/classrooms/:id/inventory"});
  this.route('access-grants', {path: "/classrooms/:id/access-grants"});
  this.route('reservations', {path: "/classrooms/:id/reservations"});
  this.route('reports');
  this.route('base-route');
  this.route('authenticated-route');
  this.route('admin-route');
  this.route('operator-route');
  this.route('teacher-route');
  this.route('logout');
  this.route('takenKeys');
  this.route('reservation');
  this.route('equipment-types');
  this.route('all-reservations');
});

export default Router;
