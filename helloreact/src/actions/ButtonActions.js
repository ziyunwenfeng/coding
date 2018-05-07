import Dispatcher from '../dispatcher/Dispatcher'
const ButtonActions =  {
    fluxDemo(){
        console.log("ButtonActions")
        Dispatcher.dispatch({
            actionType : 'FLUX'
            // text : text
        })
    }
    
}
export default ButtonActions;