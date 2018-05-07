import React, { Component } from 'react';
class CommentInput extends Component {
    constructor() {
        super()
        this.state = {
            userName: "",
            content: ""
        }
    }
    componentWillMount() {

    }
    componentDidMount() {
        this.refs.theInput.focus()
    }
    render() {
        return (
            <div>
                <div className='comment-input'>
                    <div className='comment-field'>
                        <span className='comment-field-name' onClick={this.onClickFocus.bind(this)}>用户名：</span>
                        <div className='comment-field-input'>
                            <input value={this.state.userName} onChange={this.setUserName.bind(this)}
                                ref="theInput" />
                        </div>
                    </div>
                    <div className='comment-field'>
                        <span className='comment-field-name'>评论内容：</span>
                        <div className='comment-field-input'>
                            <textarea value={this.state.content} onChange={this.setContent.bind(this)} />
                        </div>
                    </div>
                    <div className='comment-field-button'>
                        <button onClick={this.handSubmit.bind(this)}>
                            发布
          </button>
                    </div>
                </div>
            </div>
        )
    }
    setUserName(event) {
        this.setState(
            {
                userName: event.target.value
            }
        )
    }
    setContent(event) {
        console.log(event.target)
        this.setState(
            {
                content: event.target.value
            }
        )
    }
    handSubmit(event) {

        this.props.onSubmit(this.state);
    }
    onClickFocus(event) {
        this.setState(
            {
                userName: ''
            }, () => {
                this.refs.theInput.focus()
            }
        )
    }
}
export default CommentInput;