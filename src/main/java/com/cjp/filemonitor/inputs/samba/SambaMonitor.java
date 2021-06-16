package com.cjp.filemonitor.inputs.samba;

import com.cjp.filemonitor.config.AppConfig;
import com.cjp.filemonitor.inputs.GlobalMonitor;
import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.security.bc.BCSecurityProvider;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.SmbConfig;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;

import java.util.ArrayList;

public class SambaMonitor extends GlobalMonitor {


    private final AppConfig config;
    private Session session;
    private boolean isOldfilePresent;

    public SambaMonitor(AppConfig config) {
        this.config = config;
    }


    @Override
    public void initialize() throws Exception {
        SmbConfig cfg = SmbConfig.builder().withSecurityProvider(new BCSecurityProvider()).build(); // We are using BCSecurityProvider because otherwise it re-use an old SMB1 protocol and thus fails to connect to our new SMB 2 & 3 shares
        SMBClient client = new SMBClient(cfg);

        Connection connection = client.connect(config.getSmbhostName());
        AuthenticationContext ac = new AuthenticationContext(config.getSambausername(), config.getSambapassword().toCharArray(), "intranet-cjp");

        Session session = connection.authenticate(ac);

        this.session = session;

    }


    @Override
    public SambaReport analyse(long deadline) throws Exception {
        ArrayList<FileIdBothDirectoryInformation> oldFileList = new ArrayList<FileIdBothDirectoryInformation>();
        try (DiskShare share = (DiskShare) session.connectShare(config.getShareName())) {
            for (FileIdBothDirectoryInformation f : share.list(config.getSambaPath(), config.getSearchPattern())) {

                if (isTimestampLowerThan(f.getCreationTime().toEpochMillis(), deadline)) {
                    isOldfilePresent = true;
                    oldFileList.add(f);
                    System.out.println("File : " + f.getFileName());
                }

            }
        }
        return new SambaReport(config.getSambaPath(), oldFileList.size(), isOldfilePresent);
    }


    @Override
    public void close() throws Exception {
        session.close();
    }
}
