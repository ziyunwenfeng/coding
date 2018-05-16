import React, { Component } from 'react';
import User from './pages/User'
import Author from './pages/Author'
import Details from './pages/Details'
class Article extends Component{
    constructor(){
        super()
    }
    componentWillMount(){
        
    }
    componentDidMount(){

    }
    render(){
        return (
            <div>               
                <Author/>
                <Details/>
            </div>

        )
    }
}
export default  Article;