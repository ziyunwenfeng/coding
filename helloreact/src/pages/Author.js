import React, { Component } from 'react';
import ButtonActions from '../actions/ButtonActions';
import ListStore from '../store/ListStore'
class Author extends Component{
    constructor(){
        super()
        this.state = {
            author : {
                username : '',
                nicknane : '',
                birthdate : '',
                registertime : ''
            }
        }
    }
    componentWillMount(){
        let data = ButtonActions.getAuthor();
       let a = ListStore.getAuthor();
       console.log("mmm",a)
    }
    componentDidMount(){

    }
    render(){
        const comments = [
            { userName: "tang", content: 12 },
            { userName: "yuy", content: 15 },
            { userName: "zoin", content: 18 }
        ]
        return (
            <div>
                {/* {comments.map((comment,i)=>{
                    return(<div>
                        <span> {comment.userName}</span>:
                        <span> {comment.content}</span>
                        </div>)
                   
                })} */}
                <button onClick={this.getAuthor.bind(this)}>author</button>
            </div>

        )
    }
    getAuthor(event){
        let data = ButtonActions.getAuthor(event);
        let a = ListStore.getAuthor();
       console.log("m,mm",a)
    }
    
    
}
export default Author;
