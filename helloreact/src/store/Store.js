import {ReduceStore} from 'flux/utils'
import Dispatcher from '../dispatcher/Dispatcher'
class Store extends ReduceStore{
    constructor(){
        super(Dispatcher)
    }
    reduce(state,action){
        console.log("Store")
        switch(action.type){
            case 'FLUX':
                return "flux";
            default :
                return "ok";
        }
    }
}
export default Store;