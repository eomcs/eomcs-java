"use strict";

// 이벤트 리스너 등록하기

class Toggle extends React.Component {
  constructor(props) {
    super(props);
    this.state = { isToggleOn: true };

    // handleClick() 함수의 this가 이 객체를 가리키도록 묶어 준다.
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    this.setState((prevState) => ({
      isToggleOn: !prevState.isToggleOn,
    }));
  }

  render() {
    return <button onClick={this.handleClick}>{this.state.isToggleOn ? "ON" : "OFF"}</button>;
  }
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<Toggle />);
