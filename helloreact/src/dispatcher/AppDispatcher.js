// // import {Dispatcher} from 'flux';
// var AppDispatcher = new Dispatcher();
// var ListStore = require('../store/ListStore');
// AppDispatcher.register(function(action){
//     switch(action.actionType){
//         case 'flux' : 
//         ListStore.addNewItemHandler(action.text);
//         ListStore.emitChange();
//             break;
//         default :
//     }

// })
// module.exports = AppDispatcher;

var Dispatcher = require('flux').Dispatcher;
// import {Dispatcher} from 'flux';
var AppDispatcher = new Dispatcher();
var ListStore = require('../store/ListStore');

AppDispatcher.register(function (action) {
  switch (action.actionType) {
    case 'FLUX':
      let data = ListStore.addNewItemHandler(action.text);
      ListStore.emitChange();
      break;
    case 'userlogin':
      ListStore.handleLogin(action.text);
      ListStore.emitChange();
      break;
    case 'getAuthor':
      ListStore.getAuthor(action.text);
      ListStore.emitChange();
      break;
      
    default:
    // no op
  }
})

module.exports = AppDispatcher;
// import {Dispatcher} from 'flux';