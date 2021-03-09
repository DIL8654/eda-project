import React, { useState, useContext, useEffect } from 'react';

import {
    Button,
    TextField,
    Box,
    Typography,
    Container,
    Select,
    MenuItem,
    makeStyles,
    Paper,
} from '@material-ui/core';
import { useHistory } from 'react-router';
import { AppContext } from '../contexts/AppContext';
import { getAvaiability, login, getAllLocations } from '../services/Service';
import Colors from '../helpers/Colors';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

export default function Search() {

    const { authData, setAuth, setBookNowOpen, setBooking, } = useContext(AppContext);
    const history = useHistory();

    const classes = useStyles();

    // const selections = [
    //     { id: "loc1", name: "Location 1" },
    //     { id: "loc2", name: "Location 2" },
    // ];

    const [location, setLocation] = useState('');
    const [fromDate, setFromDate] = useState('');
    const [toDate, setToDate] = useState('');
    const [selections, setSelections] = useState([]);

    const [data, setData] = useState([
        {
            name: 'test',
            location: 'Colombo',
            price: '123',
        }
    ]);

    useEffect(() => {
        getAllLocations(authData).then((response) => {
            setSelections(response);
        })
        .catch(() => {
            alert("Error fetching all locations");
        });
    }, [])

    const onChangeLocation = (location) => {
        setLocation(location);
    }

    const onChangeFromDate = (location) => {
        setFromDate(location);
    }

    const onChangeToDate = (location) => {
        setToDate(location);
    }

    const onSearchClick = () => {
        getAvaiability(authData, location, fromDate, toDate).then((response) => {
            setData(response);
        }).catch(() => {
            alert("Error getting data");
        });
    }

    const onRowClick = (row) => {
        console.log(row);
        setBooking(row);
        setBookNowOpen(true);
    }

    return (
        <Box className={classes.template}>
            <Container
                component="main"
                className={classes.container}
            >
                <Box className={classes.paper}>
                    <Box className={classes.form} noValidate>
                        <Select
                            variant="outlined"
                            margin="normal"
                            fullWidth
                            onChange={(event) => onChangeLocation(event.target.value)}
                            id="location"
                            name="location"
                            className={classes.usernameInput}
                            autoFocus
                            defaultValue={"select"}
                        >
                            <MenuItem value="select">Select Location</MenuItem>
                            {
                                selections.map((item) => {
                                    return (<MenuItem value={item}>{item}</MenuItem>)
                                })
                            }
                        </Select>

                        <TextField
                            id="date"
                            label="From"
                            type="date"
                            className={classes.textField}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(event) => onChangeFromDate(event.target.value)}

                        />
                        {"          "}
                        <TextField
                            id="dateTo"
                            label="To"
                            type="date"
                            className={classes.textField}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            onChange={(event) => onChangeToDate(event.target.value)}

                        />

                        <Box className={classes.buttonContainer}>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                className={classes.submit}
                                onClick={onSearchClick}
                            >
                                {"Search"}
                            </Button>

                        </Box>
                    </Box>

                    <TableContainer component={Paper}>
                        <Table className={classes.table} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Name</TableCell>
                                    <TableCell align="right">Price</TableCell>
                                    <TableCell align="right">Location</TableCell>
                                    <TableCell align="right">Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {data.map((row) => (
                                    <TableRow key={row.id}>
                                        <TableCell component="th" scope="row">
                                            {row.name}
                                        </TableCell>
                                        <TableCell align="right">{row.price}</TableCell>
                                        <TableCell align="right">{row.address.city}</TableCell>
                                        <TableCell align="right">
                                            {
                                                <Button
                                                    type="submit"
                                                    fullWidth
                                                    variant="contained"
                                                    onClick={() => onRowClick({
                                                        ...row,
                                                        from: fromDate,
                                                        to: toDate,
                                                    })}
                                                >
                                                    {"Book"}
                                                </Button>
                                            }
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>

                </Box>
            </Container>
        </Box>
    );

}


const useStyles = makeStyles((theme) => ({
    template: {
        minHeight: 600,
        height: '100vh',
        width: '100vw',
        display: 'flex',
        justifyContent: 'flex-start',
        alignItems: 'flex-start',
        backgroundColor: 'rgba(34, 147, 249)',
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        margin: 0,
        padding: 0,
    },
    container: {
        marginTop: '8vh',
        height: '90vh',
        width: '90vw',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    buttonContainer: {
        display: 'flex'
    },
    paper: {
        width: '100%',
        height: '100%',
        padding: 30,
        borderRadius: 8,
        minWidth: 400,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        backgroundColor: 'white',
        minHeight: 200,
        paddingBottom: 0,

    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(1),
    },
    usernameInput: {
        fontFamily: 'RobotoCondensed-Regular',
        fontWeight: 500,
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
        backgroundColor: Colors.blue,
        color: Colors.white,
        height: 45,
        fontSize: 18,
        boxShadow: 'none',
        fontFamily: 'SFProDisplay-Medium',
        '&:hover': {
            backgroundColor: Colors.blue,
            boxShadow: 'none',
        },
        marginLeft: '10px',
        marginRight: '10px',
        fontWeight: 500,
    },
    logo: {
        height: 100,
        width: '30vw',
        objectFit: 'contain',
        marginBottom: 10,
    },
    title: {
        fontSize: 22,
        color: Colors.white,
        fontFamily: 'SFProDisplay-Medium',
    },
    errorText: {
        color: Colors.white,
        fontSize: 17,
        fontFamily: 'SFProDisplay-Regular',
    },
    titleContainer: {
        padding: 5,
        paddingLeft: 40,
        paddingRight: 40,
        backgroundColor: Colors.blue,
        borderRadius: 22,
        marginTop: 5,
        marginBottom: 20,
    },
    alertBox: {
        marginTop: 50,
    },
    errorIcon: {
        height: 22,
        width: 22,
        objectFit: 'contain',
        marginLeft: 20,
    },
    errorContainer: {
        borderRadius: 13,
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'row',
        backgroundColor: '#DD2222',
        marginTop: 35,
        paddingLeft: 25,
        paddingRight: 25,
        paddingTop: 10,
        paddingBottom: 10,
    },
}));