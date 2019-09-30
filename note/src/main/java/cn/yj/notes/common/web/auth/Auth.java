package cn.yj.notes.common.web.auth;

/**
 * <p>
 *
 * </p>
 *
 * @author 永健
 * @since 2019-09-29 22:57
 */
public interface Auth
{
   String getToken(String headerName);
   boolean isReqToken();
}
