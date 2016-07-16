/**
 * Created by Dexter on 7/16/2016.
 */

import {GET_FILES} from '../constants/actionTypes';
import {times} from 'ramda';
import axios from 'axios';

const URL_ROOT = 'http://localhost:8080/';

const toUrl = (endpoint) => URL_ROOT + endpoint;

export function getFiles() {
    return dispatch => {
        axios.get(toUrl('getFiles'))
            .then(resp => {
                return dispatch({
                    type: GET_FILES,
                    payload: resp.data
                });
            })
            .catch(err => {

            });
        // const payload = times(i => ({
        //     id: i,
        //     filename: i,
        //     size: i * 10,
        //     time: Date.now() - (i * 1000),
        //     href: 'http://foo.com/asdf/fasdf'
        // }), 500);
    };
}