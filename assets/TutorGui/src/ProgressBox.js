import React, {Component} from 'react';
import { ProgressBar } from 'react-bootstrap'

class ProgressBox extends Component{
  render(){
    var percent = ((this.props.current -1) /this.props.questions.length*100);

    return(
      <div>
        <div>Progress: <ProgressBar now={percent} label={`${percent}%`} /></div>
      </div>
      )
  }
}

export default ProgressBox
