import AppDispatcher from '../dispatcher/AppDispatcher'
const ButtonActions = {

  addNewItem: function (text) {
    AppDispatcher.dispatch({
      actionType: 'FLUX',
      text: 'text'
    });
  },
  handleLogin(userinfo){
    
    AppDispatcher.dispatch({
      actionType : "userlogin",
      text : userinfo
    })
  },
  getAuthor(event){
    AppDispatcher.dispatch({
      actionType : "getAuthor",
      text : event
    })
  }

}
export default ButtonActions;
