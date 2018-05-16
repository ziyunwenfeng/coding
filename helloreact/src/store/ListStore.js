
// import Dispatcher from '../dispatcher/Dispatcher'
// class Store {
//     constructor(){
//         super(Dispatcher)
//     }
   
    
// }
// export default Store;

// import PublicContext from '../public/PublicContext'
var EventEmitter = require('events').EventEmitter;
var assign = require('object-assign');

var ListStore = assign({}, EventEmitter.prototype, {
  items: [],
  getAll: function () {
    return this.items;
  },

  addNewItemHandler: function (text) {
    this.items.push(text);
    return this.items;
  },

  handleLogin(userinfo){
    console.log("store userinfo",userinfo)
    console.log("name",userinfo.username)
  },

  getAuthor(event){
    console.log(" !!!!!!!!")
    return "author";
  },

  emitChange: function () {
    this.emit('change');
  },

  addChangeListener: function(callback) {
    this.on('change', callback);
  },

  removeChangeListener: function(callback) {
    this.removeListener('change', callback);
  }
});

module.exports = ListStore;
