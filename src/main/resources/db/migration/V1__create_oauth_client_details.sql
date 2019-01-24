CREATE TABLE oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

CREATE TABLE oauth_access_token (
  authentication_id VARCHAR(255) NOT NULL,
  token_id VARCHAR(255) NOT NULL,
  token BLOB NOT NULL,
  user_name VARCHAR(255) NOT NULL,
  client_id VARCHAR(255) NOT NULL,
  authentication BLOB NOT NULL,
  refresh_token VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(255) NOT NULL,
  token BLOB NOT NULL,
  authentication BLOB NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;