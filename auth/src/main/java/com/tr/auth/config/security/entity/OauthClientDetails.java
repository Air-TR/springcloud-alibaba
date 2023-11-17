package com.tr.auth.config.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author: TR
 *
 *  CREATE TABLE `oauth_client_details`  (
 *   `client_id` varchar(256) COMMENT '客户端ID',
 *   `resource_ids` varchar(256),
 *   `client_secret` varchar(256) COMMENT '客户端密钥',
 *   `scope` varchar(256),
 *   `authorized_grant_types` varchar(256) COMMENT '授权类型',
 *   `web_server_redirect_uri` varchar(256),
 *   `authorities` varchar(256),
 *   `access_token_validity` int(11) COMMENT 'access_token的有效时间',
 *   `refresh_token_validity` int(11) COMMENT 'refresh_token的有效时间',
 *   `additional_information` varchar(4096),
 *   `autoapprove` varchar(256) COMMENT '是否允许自动授权',
 *   PRIMARY KEY (`client_id`) USING BTREE
 *  ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
 */
@Data
@Entity
public class OauthClientDetails {

    @Id
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;

}
