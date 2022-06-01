const RegexProperties = {
  IDCHECK: /^[A-Za-z]+[A-Za-z0-9]+$/,
  PASSWORD: /^[A-Za-z]+[A-Za-z0-9]+$/,
  USERNAME: /^[A-Za-z가-힣]+$/,
  ADDRESS: /^[A-Za-z가-힣\\s]+$/,
  PHONENUMBER: /^[0-9]{11}$/,
};

export default RegexProperties;
