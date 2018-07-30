CREATE OR REPLACE FUNCTION f_get_project_pv (
    v_project_com   IN VARCHAR2,
    v_region        IN VARCHAR2,
    v_sub_id        IN VARCHAR2,
    v_product       IN VARCHAR2,
    v_color         IN VARCHAR2,
    v_month         IN VARCHAR2
)

/*------------------------------------------------------------------------------------------------
	 DESCRIPTION : F_GET_PROJECT_PV 조회  
------------------------------------------------------------------------------------------------*/ RETURN FLOAT IS
    v_error_code   NUMBER;
    v_error_msg    VARCHAR2(500);
    r_pv           NUMBER := 0;
    PRAGMA autonomous_transaction;
BEGIN
    BEGIN
        SELECT
            DECODE(v_product,'C-LBP',DECODE(manual_type,'FIXED',lbp_pv_m,lbp_pv),
                             'M-LBP',DECODE(manual_type,'FIXED',lbp_pv_m,lbp_pv),
                             'C-MFP',DECODE(manual_type,'FIXED',mfp_pv_m,mfp_pv),
                             'L-MFP',DECODE(manual_type,'FIXED',mfp_pv_m,mfp_pv),0)
                              * 
            DECODE(v_color,'COLOR',DECODE(manual_type,'FIXED',color_mix_m,color_mix),1 - DECODE(manual_type,'FIXED',color_mix_m,color_mix) )
        INTO
            r_pv
        FROM
            pbfs_master_set_pv s
        WHERE
            s.project_com = v_project_com
            AND   s.sub_id = v_sub_id
            AND   s.year_month = v_month;

    EXCEPTION
        WHEN no_data_found THEN
            BEGIN
                SELECT
                    DECODE(v_product,'C-LBP',DECODE(manual_type,'FIXED',lbp_pv_m,lbp_pv),'M-LBP',DECODE(manual_type,'FIXED',lbp_pv_m,lbp_pv),'C-MFP',
DECODE(manual_type,'FIXED',mfp_pv_m,mfp_pv),'L-MFP',DECODE(manual_type,'FIXED',mfp_pv_m,mfp_pv),0) * DECODE(v_color,'COLOR',DECODE(
manual_type,'FIXED',color_mix_m,color_mix),1 - DECODE(manual_type,'FIXED',color_mix_m,color_mix) )
                INTO
                    r_pv
                FROM
                    pbfs_master_set_pv s
                WHERE
                    s.project_com = v_project_com
                    AND   s.region = (
                        SELECT
                            MAX(region_cd)
                        FROM
                            pbfs_master_subsidiary
                        WHERE
                            sub_id = v_sub_id
                    )
                    AND   s.sub_id = 'ALL'
                    AND   s.year_month = v_month;

            EXCEPTION
                WHEN no_data_found THEN
                    BEGIN
                        SELECT
                            DECODE(v_product,'C-LBP',DECODE(manual_type,'FIXED',lbp_pv_m,lbp_pv),'M-LBP',DECODE(manual_type,'FIXED',lbp_pv_m,lbp_pv),'C-MFP',
DECODE(manual_type,'FIXED',mfp_pv_m,mfp_pv),'L-MFP',DECODE(manual_type,'FIXED',mfp_pv_m,mfp_pv),0) * DECODE(v_color,'COLOR',DECODE(
manual_type,'FIXED',color_mix_m,color_mix),1 - DECODE(manual_type,'FIXED',color_mix_m,color_mix) )
                        INTO
                            r_pv
                        FROM
                            pbfs_master_set_pv s
                        WHERE
                            s.project_com = v_project_com
                            AND   s.region = 'ALL'
                            AND   s.sub_id = 'ALL'
                            AND   s.year_month = v_month;

                    EXCEPTION
                        WHEN no_data_found THEN
                            v_error_code := sqlcode;
                            v_error_msg := sqlerrm
                            || ' V_PROJECT_COM: '
                            || v_project_com
                            || ', V_SUB_ID: '
                            || v_sub_id
                            || ', V_PRODUCT: '
                            || v_product
                            || ', V_COLOR: '
                            || v_color
                            || ', V_MONTH: '
                            || v_month;

                            INSERT INTO error_log (
                                code,
                                object_name,
                                message,
                                create_user,
                                create_date,
                                project_com,
                                region,
                                sub_id,
                                color_type
                            ) VALUES (
                                v_error_code,
                                'F_GET_PROJECT_PV',
                                v_error_msg,
                                '',
                                SYSDATE,
                                '',
                                '',
                                '',
                                ''
                            );

                            COMMIT;
                            r_pv := 0;
                    END;
            END;
    END;

    dbms_output.put_line('F_GET_PROJECT_PV:'
    || TO_CHAR(sqlcode)
    || '-'
    || sqlerrm);

    RETURN r_pv;
END;