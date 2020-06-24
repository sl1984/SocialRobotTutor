import React, {Component} from 'react'

class Results extends Component{

    render(){
        var percent = (this.props.score/this.props.questions.length*100);

        if(percent > 80){
            var message='Congratulations! You scored more than 80%';
        }else if(percent < 80 && percent > 40){
            var message='Good Job! However your score is less than 80%';
        }else{
            var message = 'You have scored less than 40%. Better Luck Next Time!';
        }

        return(
          <div>
            <h2>{message}</h2>
          </div>
        )
    }
}

export default Results
