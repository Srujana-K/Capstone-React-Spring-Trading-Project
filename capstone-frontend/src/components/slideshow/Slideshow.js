import Carousel from 'react-bootstrap/Carousel'
import './Slideshow.css';

function Slideshow({items}) {
  console.log(items.length);
  const getTags = () =>{
    let tags = items.map((item)=>{
      console.log(item);
      return <Carousel.Item className="item-cls">
        <Carousel.Caption>
          <h5>Name: {item.instrumentName}</h5>
          <p>Face Value: {item.faceValue}</p>
          <p>Expiry Date: {item.expiryDate == null ? 'NA' : item.expiryDate}</p>
        </Carousel.Caption>
      </Carousel.Item>
    })
    return tags;
  }
  return (
    <Carousel className="slide">
      {getTags()}
    </Carousel>
  )
}

export default Slideshow;