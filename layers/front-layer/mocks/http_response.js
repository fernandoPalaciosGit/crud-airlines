export default (response) => ({
    ok: true,
    json: () => Promise.resolve(response || {}),
    text: () => Promise.resolve(response || ''),
});
