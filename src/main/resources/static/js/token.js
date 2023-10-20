document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('jwt_form');
  const resultAreaElement = document.getElementById('generated_jwt_text');
  const payloadElement = document.getElementById('payload');
  const copyButton = document.getElementById('copy-button');

  payloadElement.value = jsonFormat(payloadElement.value, 2);

  form.addEventListener('submit', (event) => {
    event.preventDefault();

    const requestBody = {};
    requestBody.payload = jsonFormat(payloadElement.value, 0);

    const httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', form.action, true);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.onload = () => {
        if (httpRequest.status === 201) {
            const responseBody = JSON.parse(httpRequest.responseText);
            resultAreaElement.value = responseBody.accessToken;
        } else {
            alert(httpRequest.responseText);
        }
    };
    httpRequest.onerror = () => {
        alert(httpRequest.responseText);
    };
    httpRequest.send(JSON.stringify(requestBody));
  });
  copyButton.onclick = () => {
    const textValue = resultAreaElement.value;
    if (textValue) {
        copyToClipboard(resultAreaElement.value);
    }
  };
});

const jsonFormat = (text, space) => {
  return JSON.stringify(JSON.parse(text), null, space);
}

const copyToClipboard = (tagValue) => {
  if (navigator.clipboard) {
    return navigator.clipboard.writeText(tagValue).then(() => {
      alert('コピーしました。');
    });
  } else {
    tagText.select()
    document.execCommand('copy');
    alert('コピーしました。');
  }
}
