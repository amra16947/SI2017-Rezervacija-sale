import Ember from 'ember';
import ResetScrollTopMixin from 'client/mixins/reset-scroll-top';
import { module, test } from 'qunit';

module('Unit | Mixin | reset scroll top');

// Replace this with your real tests.
test('it works', function(assert) {
  let ResetScrollTopObject = Ember.Object.extend(ResetScrollTopMixin);
  let subject = ResetScrollTopObject.create();
  assert.ok(subject);
});
