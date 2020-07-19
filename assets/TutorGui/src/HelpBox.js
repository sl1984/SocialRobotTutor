import React, {Component} from 'react'

class HelpBox extends Component{

    constructor(props) {
          super(props)
     }

    render(){
        let {help} = this.props

        return(
          <div className="well">
            <div><h4><b>Help Box</b></h4></div>
            <div>{help}</div>
          </div>
        )
    }
}

export default HelpBox
