import React, { Component } from 'react';
import ButtonActions from '../actions/ButtonActions';

class User extends Component {
    constructor() {
        super()
        
        this.state = {
            userinfo: {
                username : '',
                passwd : ''
            }
        }
    }
    
    componentWillMount() {

    }
    componentDidMount() {

    }
    render() {
        return (
            <div>
                {/* <input /> */}
                <input value={this.state.userinfo.username} onChange={this.setUserName.bind(this)} placeholder="请输入用户名"/>   
                <input value={this.state.userinfo.passwd} onChange={this.setPasswd.bind(this)} placeholder="请输入密码"/>
            
                <button onClick={this.handleLogin.bind(this)}>login</button>
            </div>

        )
    }

    setUserName(event){
        // console.log("dddddddd",this.state.userinfo)
        console.log("name",event.target)
        let username = event.target.value ;
        this.state.userinfo.username = username;
        this.setState({userinfo : this.state.userinfo})
    }
    setPasswd(event){
        console.log("psaa",event.target)
        let passwd = event.target.value ;
        this.state.userinfo.passwd = passwd;
        this.setState({userinfo : this.state.userinfo})
    }
    handleLogin(){
        console.log("dddddddd",this.state.userinfo)
        // ButtonActions.handleLogin(this.state.userinfo)
        let history = this.props.history;
        console.log("history",history)
        if(this.state.userinfo.username=="1"&&this.state.userinfo.passwd =="1"){
            history.push('/article')
        }else{
            // alter("error")
        }
        
    }
}
export default User;