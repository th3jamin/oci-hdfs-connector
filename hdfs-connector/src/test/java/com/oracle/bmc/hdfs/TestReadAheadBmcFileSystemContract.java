package com.oracle.bmc.hdfs;

import com.oracle.bmc.hdfs.contract.BmcContract;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestReadAheadBmcFileSystemContract extends TestBmcFileSystemContract {

    public static Path INITIAL_WORKING_DIRECTORY;

    @BeforeClass
    public static void setUpStatic() throws Exception {
        final Configuration configuration = new Configuration();
        final BmcContract.ReadAhead contract = new BmcContract.ReadAhead(configuration);
        contract.init();
        INITIAL_WORKING_DIRECTORY = contract.getTestFileSystem().getWorkingDirectory();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        final Configuration configuration = new Configuration();
        final BmcContract.ReadAhead contract = new BmcContract.ReadAhead(configuration);
        contract.init();
        super.fs = contract.getTestFileSystem();

        // reset the working directory to avoid test-to-test influence
        fs.setWorkingDirectory(INITIAL_WORKING_DIRECTORY);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        super.fs.delete(new Path("/existingobjects"), true);
    }
}
