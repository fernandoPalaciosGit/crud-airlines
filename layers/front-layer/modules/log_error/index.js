import http from '../http_request';

const SERVICE = '/logger/front';
const STACK_JOIN_LABEL = '\n\t... ';

const serviceJsLogger = (message) =>
    http.post(SERVICE, {
        message,
        hash: window.location.hash,
        queryString: window.location.search.substr(1),
    });

const parseStackTrace = (message, stackFrames) => {
    const stackTraceError = stackFrames.map((sf) => sf.toString()).join(STACK_JOIN_LABEL);

    return serviceJsLogger(`${message}${STACK_JOIN_LABEL}${stackTraceError}`);
};

export default {
    message: (msg = '') => {
        window.StackTrace.get().then((stackFrames) => parseStackTrace(msg, stackFrames));
    },
    error: (error, msg = '') => {
        window.StackTrace.fromError(error).then((stackFrames) => parseStackTrace(msg, stackFrames));
    },
};
