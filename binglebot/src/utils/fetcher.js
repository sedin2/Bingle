const axios = require('axios');

async function fetcher(url) {
  const response = await axios.get(url);
  return response;
}

module.exports = fetcher;
