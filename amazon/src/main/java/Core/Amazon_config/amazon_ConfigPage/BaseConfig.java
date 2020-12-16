package Core.Amazon_config.amazon_ConfigPage;

import Core.Amazon_config.amazon_config.ConfigProperties;
import data.UserEntity;
import helpers.FileConfig;

public class BaseConfig {
  public static final String env = System.getProperty("env");
  public static FileConfig appConfig = new ConfigProperties();
  public String getRunEnv = appConfig.getPropertyByKey("run.env");
  public String amazonUrl = appConfig.getPropertyByKey(env + ".amazon.url");



  public UserEntity getUser() {
    UserEntity userEntity = new UserEntity();
    userEntity.setSignInUrl(amazonUrl);
    return userEntity;
  }

}
