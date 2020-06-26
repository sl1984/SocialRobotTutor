import React, {Component} from 'react'

class HelpBox extends Component{

    constructor(props) {
          super(props)
     }

    render(){
        let {help} = this.props

        return(
          <div className="well">
            <div><h4>Help Box</h4></div>
            <div>{help}</div>
          </div>
        )
    }
}

export default HelpBox
