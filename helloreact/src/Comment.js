import React, { Component } from 'react';
class Comment extends Component {
    // constructor() {
    //     super()
    // }
    componentWillMount() {

    }
    componentDidMount() {

    }
    render() {
        return (
            <div>
                <div className='comment'>
                    <div className='comment-user'>
                    {console.log("))))))")}
                    {this.props.children}
                        <span>{this.props.content.userName} </span>：
                    </div>
                    <p>{this.props.content.content}</p>
                </div>
            </div>
        )
    }
}
export default Comment;