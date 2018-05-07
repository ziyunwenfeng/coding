import React, { Component } from 'react';
import Comment from "./Comment"
class CommentList extends Component {
    // constructor() {
    //     super()
    // }
    componentWillMount() {
    }
    componentDidMount() {
    }
    render() {
        // const comments = [
        //     { userName: "tang", content: 12 },
        //     { userName: "yuy", content: 15 },
        //     { userName: "zoin", content: 18 }
        // ]
        return (
            <div>
                {
                    this.props.comment.map((content,i) => <Comment content = {content} key={i}/>)
                }
            </div>
        )
    }
}
export default CommentList;