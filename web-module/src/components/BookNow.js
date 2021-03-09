import React, { useContext, useEffect, useState } from 'react';
import Modal from 'styled-react-modal'
import { AppContext } from '../contexts/AppContext';
import styled from "styled-components";
import { useHistory } from 'react-router';
import { pay, createBooking } from '../services/Service';

const StyledModal = Modal.styled`
width: 40vw;
height: 60vh;
display: flex;
align-items: center;
justify-content: center;
border-radius: 4.4px;
background-color: #ffffff;
color: #000000;
`;

const Container = styled.div`
display: flex;
flex-direction: column;
width: 100%;
`;

const RowWrapper = styled.div`
overflow-x: hidden;
overflow-y: hidden;
width: 100%;
text-align: center;
display: flex;
align-items: center;
`;

const Cell = styled.div`
overflow-x: hidden;
overflow-y: hidden;
width: 100%;
display: flex;
flex: ${props => props.flex};
margin: 10px;
`;
const CustomText = styled.p`
font-size: 16.5px;
font-weight: 500;
font-stretch: normal;
font-style: normal;
line-height: normal;
letter-spacing: normal;
display: flex;
flex: 1;
white-space: nowrap;
font-family: DINNextLTPro-Regular;
`;


export function BookNow() {
    const history = useHistory();
    const { bookNowOpen, setBookNowOpen, setAuth, authData, booking, user } = useContext(AppContext);

    const [payMode, setPayMode] = useState(false);

    const [bookingResult, setBookingResult] = useState(null);
    const [receipt, setReceipt] = useState(null);

    const [cardNumber, setCardNumber] = useState('');
    const [cvv, setCvv] = useState('');


    const onClose = () => {
        setBookNowOpen(false);
        setPayMode(false);
    }

    const onBookNowClick = () => {

        createBooking(authData, {
            "reservationRef": null,
            "propertyId": booking.id,
            "bookingStatus": "BOOKING_CREATED",
            "paymentRefId": null,
            "userId": user,
        }).then((response) => {
            setBookingResult(response);
            setPayMode(true);
        }).catch(() => {
            alert('Failed to create booking');
        });
    }

    const onPayment = () => {
        pay(authData,{
            cardNumber: cardNumber,
            cvv: cvv,
            paymentAmount: booking.price,
            bookingRef: bookingResult.reservationRef,
            userId: user,
        }).then((response) => {
            setReceipt(response);
        })
            .catch(() => {
                alert("Payment failed");
            });
    }


    return (
        <StyledModal
            isOpen={bookNowOpen}
            onBackgroundClick={onClose}
            onEscapeKeydown={onClose}
        >
            {!payMode && <Container>

                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Name:  ${booking.name}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Price:  ${booking.price}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Location:  ${booking.address}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Discount:  ${booking.discount || 0}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`From:  ${booking.from}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`To:  ${booking.to}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>

                <RowWrapper>
                    <Cell flex={1}>
                        <button onClick={() => { onBookNowClick() }} style={{ width: "120px" }}>
                            Book Now
                        </button>
                    </Cell>
                </RowWrapper>

            </Container>}

            {payMode && !receipt && <Container>

                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Card Number: `}
                            <input style={{ border: 'solid', borderColor: '#111111' }} type="text"></input>
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`CVV:        `}
                            <input style={{ border: 'solid', borderColor: '#111111' }} type="text"></input>
                        </CustomText>
                    </Cell>
                </RowWrapper>

                <RowWrapper>
                    <Cell flex={1}>
                        <button onClick={() => onPayment()} style={{ width: "120px" }}>
                            Pay Now
                        </button>
                    </Cell>
                </RowWrapper>

            </Container>}

            {receipt && <Container>

                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Booking Ref:  ${receipt.bookingRef}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Payment Ref:  ${receipt.paymentRef}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Property Name:  ${booking.name}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>
                <RowWrapper>
                    <Cell flex={1}>
                        <CustomText>
                            {`Payment:  ${receipt.amount}`}
                        </CustomText>
                    </Cell>
                </RowWrapper>

            </Container>}

        </StyledModal>
    );
}