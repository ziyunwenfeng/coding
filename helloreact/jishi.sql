CREATE OR REPLACE PROCEDURE p_pbfs_master_pv_avg (
    v_year_month IN VARCHAR2  -- PV 마스터 생성월 YYYYMM
) IS
/*------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------*/

    v_error_code    NUMBER;
    v_error_msg     VARCHAR2(200);
    v_user          VARCHAR2(20);
    v_err_step      VARCHAR2(20);
    v_year          VARCHAR2(4);
    v_quater        VARCHAR2(10);
    v_month         VARCHAR2(2) := substr(v_year_month,5,6);
    p_year_month    VARCHAR2(20);
    p_project_com   VARCHAR2(50);
    p_region        VARCHAR2(30);
    p_sub_id        VARCHAR2(30);
    p_set_plc_pv    NUMBER := 0;
    p_l_set_pv      NUMBER := 0;
    p_m_set_pv      NUMBER := 0;
    
    /* 
    -- PV기준월 : V_YYYYMM   (HISTORY 마지막월)--
    201411월 부터  V_YEAR_MONTH 와 V_YYYYMM 동일
    201412월 pv최종월   V_YYYYMM = '201412' 고정     
    */
    v_yyyymm        VARCHAR2(20) := '201803'; -- PV기준월
    v_to_yyyymm     VARCHAR2(20) := '201812'; -- 마지막생성월
    v_avg_month     NUMBER :=-6;  -- 6개월 평균
    v_month1        NUMBER := 15; -- 운영기간  15개월 이상 
    v_month2        NUMBER :=-9; -- 운영기간  15개월 - 6개월(=>-9개월 이상) 
    
    /*
    KEY  :  YEAR_MONTH, PROJECT_COM, REGION, SUB_ID
    */
BEGIN
            
/*
      IF (V_YYYYMM = '03' OR V_YYYYMM = '06' OR V_YYYYMM = '09' OR V_YYYYMM = '18') THEN
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(V_ERROR_CODE) || '-' || V_ERROR_MSG);
      ELSE
        RETURN;
      END IF;        
  */
    SELECT
        year,
        quater
    INTO
        v_year,v_quater
    FROM
        pm_calendar
    WHERE
        year
        || month = TO_CHAR(add_months(TO_DATE(v_year_month,'YYYYMM'),0),'YYYYMM');

    DELETE FROM pbfs_master_set_pv
    WHERE
        year_month = v_year_month
        AND   manual_yn = 'N';

    COMMIT;
    v_err_step := '100';

        -- 1. PV 생성 : 생성월 V_YYYYMM --
   --     INSERT INTO PBFS_MASTER_SET_PV ( 
    INSERT INTO pbfs_master_set_pv (
        year_month,
        year,
        quater,
        segment,
        project_color,
        project_com,
        region,
        sub_id,
        set_plc_pv,
        sub_pv,
        region_pv,
        ww_pv,
        create_date,
        create_user,
        avg_dev,
        avg_yn,
        base_yyyymm,
        color_mix
    )
        WITH w_param AS (
        -- 이달 pv 가지고 다음달 미래손익을 산출하기 때문에 다음달 set를 대상으로함.--
            SELECT
                v_year_month AS year_month,
                b.buffer1 AS project_com,
                c.region,
                c.site,
                MAX(DECODE(b.segment_com,'A3',nvl(s.segment_com2,b.segment_com),'SEG1',nvl(s.segment_com2,b.segment_com),'SEG2',nvl(s.segment_com2
,b.segment_com),'SEG3',nvl(s.segment_com2,b.segment_com),'SEG4',nvl(s.segment_com2,b.segment_com),b.segment_com) ) segment_com,
                upper(c.color) AS project_color,
                months_between(TO_DATE(MAX(c.year
                || c.month),'YYYYMM'),TO_DATE(MIN(c.year
                || c.month),'YYYYMM') ) gap,
                CASE
                        WHEN months_between(TO_DATE(v_yyyymm,'YYYYMM'),TO_DATE(MIN(c.year
                        || c.month),'YYYYMM') ) >= v_month1 THEN 'Y'
                        ELSE 'N'
                    END
                target_flag,
                MAX(p.include_pv) include_pv
            FROM
                pbfs_sales_connect c,
                pm_master_basic b,
                (
                    SELECT
                        *
                    FROM
                        pm_master_project_com
                    WHERE
                        region = 'ALL'
                        AND   sub_id = 'ALL'
                ) p,
                (
                    SELECT
                        comncd AS project_com,
                        comcd_nm AS segment_com2
                    FROM
                        pbfs_common_cd
                    WHERE
                        comncd_tcd = 'SEGMENT_GROUP'
                ) s
            WHERE
                1 = 1
                AND   c.project_com = s.project_com (+)
                AND   c.year
                || c.month <= v_to_yyyymm
                AND   c.supplies_type = 'SET'
                AND   c.lab_office = 'S'
                AND   c.basic = b.basic
                AND   c.s_qty <> 0
                AND   c.color != '-'
                AND   b.buffer1 = c.project_com
                AND   c.project_com = p.project_com (+)
                AND   c.project_com
                || c.region
                || c.site != 'OUTSOURCING(TOSHIBA)한국한국'
            GROUP BY
                b.buffer1,
                c.region,
                c.site,
                b.segment_com,
                c.color
            UNION ALL
            SELECT
                v_year_month AS year_month,
                c.project_com,
                c.region,
                c.sub_id,
                DECODE(b.segment_com,'A3',nvl(s.segment_com2,b.segment_com),'SEG1',nvl(s.segment_com2,b.segment_com),'SEG2',nvl(s.segment_com2,b.
segment_com),'SEG3',nvl(s.segment_com2,b.segment_com),'SEG4',nvl(s.segment_com2,b.segment_com),b.segment_com) segment_com,
                upper(c.project_color) AS project_color,
                0 gap,
                'Y' target_flag,
                'N' include_pv
            FROM
                pm_master_project_com c,
                (
                    SELECT DISTINCT
                        buffer1,
                        segment_com
                    FROM
                        pm_master_basic
                ) b,
                (
                    SELECT
                        comncd AS project_com,
                        comcd_nm AS segment_com2
                    FROM
                        pbfs_common_cd
                    WHERE
                        comncd_tcd = 'SEGMENT_GROUP'
                ) s
            WHERE
                1 = 1
                AND   c.project_com = s.project_com (+)
                AND   c.forecast_yn = 'Y'
                AND   c.color_type = 'Mono'
                AND   c.sub_id = 'ALL'
                AND   c.project_com = b.buffer1 (+)
        ),sub_pv AS (  -- 자기 법인 PV
            SELECT
                'SELF' type,
                MAX(year_month) year_month,
                b.segment_com,
                b.segment_com2,
                upper(b.project_color) AS project_color,
                h.project_com,
                h.region,
                h.sub_id,
                DECODE(SUM(DECODE(h.color_type,'Mono',h.set_qty,0) ),0,0, (SUM(h.mm_for_qty * b.after_toner) / SUM(DECODE(h.color_type,'Mono',h.set_qty
,0) ) ) ) sub_pv,
                round(DECODE(SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ),0,0, (SUM(DECODE(h.color_type,'Color',DECODE(h.year_month
,v_yyyymm,h.mm_for_qty * h.after_toner,0),0) ) / SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ) ) ),6) sub_color_mix
            FROM
                (
                    SELECT
                        year_month,
                        project_com,
                        region,
                        sub_id,
                        color_type,
                        set_qty,
                        mm_for_qty,
                        after_toner
                    FROM
                        pbfs_set_plc_print_hist a
                    WHERE
                        EXISTS (
                            SELECT
                                1
                            FROM
                                pbfs_set_plc_print_hist b
                            WHERE
                                a.project_com = b.project_com
                                AND   a.region = b.region
                                AND   a.sub_id = b.sub_id
                                AND   a.color_type = b.color_type
                                AND   b.year_month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_month2),'YYYYMM')
                        )
                ) h,
                (
                    SELECT
                        MAX(b.segment_com) AS segment_com,
                        nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type,
                        MAX(p.after_toner) AS after_toner,
                        MAX(p.include_pv) AS include_pv
                    FROM
                        pm_master_basic b,
                        pm_master_project_com p,
                        (
                            SELECT
                                comcd_desc AS segment,
                                comcd_nm AS segment_com2,
                                comncd AS project_com
                            FROM
                                pbfs_common_cd
                            WHERE
                                comncd_tcd = 'SEGMENT_GROUP'
                        ) s
                    WHERE
                        p.project_com = b.buffer1
                        AND   p.forecast_yn = 'Y'
                        AND   p.project_com = s.project_com (+)
                    GROUP BY
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type
                ) b
            WHERE
                1 = 1
                AND   h.project_com = b.project_com
                AND   h.region = b.region
                AND   h.sub_id = b.sub_id
                AND   h.color_type = b.color_type
                AND   h.region != 'ALL'
                AND   h.year_month IN (
                    SELECT
                        year_month
                    FROM
                        pm_calendar
                    WHERE
                        year_month BETWEEN TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_avg_month),'YYYYMM') AND v_yyyymm
                )
            GROUP BY
                b.segment_com,
                b.segment_com2,
                b.project_color,
                h.project_com,
                h.region,
                h.sub_id
        ),seg_sub_pv AS ( -- SEG 평균 (법인) PV 
            SELECT
                'SUB' type,
                MAX(year_month) year_month,
                b.segment_com,
                b.segment_com2,
                upper(b.project_color) AS project_color,
                h.region,
                h.sub_id,
                DECODE(SUM(DECODE(h.color_type,'Mono',h.set_qty,0) ),0,0, (SUM(h.mm_for_qty * b.after_toner) / SUM(DECODE(h.color_type,'Mono',h.set_qty
,0) ) ) ) seg_sub_pv,
                round(DECODE(SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ),0,0, (SUM(DECODE(h.color_type,'Color',DECODE(h.year_month
,v_yyyymm,h.mm_for_qty * h.after_toner,0),0) ) / SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ) ) ),6) seg_sub_color_mix
            FROM
                (
                    SELECT
                        year_month,
                        project_com,
                        region,
                        sub_id,
                        color_type,
                        set_qty,
                        mm_for_qty,
                        after_toner
                    FROM
                        pbfs_set_plc_print_hist a
                    WHERE
                        EXISTS (
                            SELECT
                                1
                            FROM
                                pbfs_set_plc_print_hist b
                            WHERE
                                a.project_com = b.project_com
                                AND   a.region = b.region
                                AND   a.sub_id = b.sub_id
                                AND   a.color_type = b.color_type
                                AND   b.year_month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_month2),'YYYYMM')
                        )
                ) h,
                (
                    SELECT
                        MAX(b.segment_com) AS segment_com,
                        nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type,
                        MAX(p.after_toner) AS after_toner,
                        MAX(p.include_pv) AS include_pv
                    FROM
                        pm_master_basic b,
                        pm_master_project_com p,
                        (
                            SELECT
                                comcd_desc AS segment,
                                comcd_nm AS segment_com2,
                                comncd AS project_com
                            FROM
                                pbfs_common_cd
                            WHERE
                                comncd_tcd = 'SEGMENT_GROUP'
                        ) s
                    WHERE
                        p.project_com = b.buffer1
                        AND   p.forecast_yn = 'Y'
                        AND   p.project_com = s.project_com (+)
                    GROUP BY
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type
                ) b
            WHERE
                1 = 1
                AND   h.project_com = b.project_com
                AND   h.region = b.region
                AND   h.sub_id = b.sub_id
                AND   h.color_type = b.color_type
                AND   h.region != 'ALL'
                AND   b.include_pv = 'Y'
                AND   h.year_month IN (
                    SELECT
                        year_month
                    FROM
                        pm_calendar
                    WHERE
                        year_month BETWEEN TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_avg_month),'YYYYMM') AND v_yyyymm
                )
            GROUP BY
                b.segment_com,
                b.segment_com2,
                b.project_color,
                h.region,
                h.sub_id
        ),seg_reg_pv AS ( -- SEG 평균 (지역) PV 
            SELECT
                'REGION' type,
                MAX(year_month) year_month,
                b.segment_com,
                b.segment_com2,
                upper(b.project_color) AS project_color,
                h.region,
                h.sub_id,
                DECODE(SUM(DECODE(h.color_type,'Mono',h.set_qty,0) ),0,0, (SUM(h.mm_for_qty * b.after_toner) / SUM(DECODE(h.color_type,'Mono',h.set_qty
,0) ) ) ) seg_reg_pv,
                round(DECODE(SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ),0,0, (SUM(DECODE(h.color_type,'Color',DECODE(h.year_month
,v_yyyymm,h.mm_for_qty * h.after_toner,0),0) ) / SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ) ) ),6) seg_reg_color_mix
            FROM
                (
                    SELECT
                        year_month,
                        project_com,
                        region,
                        sub_id,
                        color_type,
                        set_qty,
                        mm_for_qty,
                        after_toner
                    FROM
                        pbfs_set_plc_print_hist a
                    WHERE
                        EXISTS (
                            SELECT
                                1
                            FROM
                                pbfs_set_plc_print_hist b
                            WHERE
                                a.project_com = b.project_com
                                AND   a.region = b.region
                                AND   a.sub_id = b.sub_id
                                AND   a.color_type = b.color_type
                                AND   b.year_month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_month2),'YYYYMM')
                        )
                ) h,
                (
                    SELECT
                        MAX(b.segment_com) AS segment_com,
                        nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type,
                        MAX(p.after_toner) AS after_toner,
                        MAX(p.include_pv) AS include_pv
                    FROM
                        pm_master_basic b,
                        pm_master_project_com p,
                        (
                            SELECT
                                comcd_desc AS segment,
                                comcd_nm AS segment_com2,
                                comncd AS project_com
                            FROM
                                pbfs_common_cd
                            WHERE
                                comncd_tcd = 'SEGMENT_GROUP'
                        ) s
                    WHERE
                        p.project_com = b.buffer1
                        AND   p.forecast_yn = 'Y'
                        AND   p.project_com = s.project_com (+)
                    GROUP BY
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type
                ) b
            WHERE
                1 = 1
                AND   h.project_com = b.project_com
                AND   h.region = b.region
                AND   h.sub_id = b.sub_id
                AND   h.color_type = b.color_type
                AND   h.region != 'ALL'
                AND   h.sub_id = 'ALL'
                AND   b.include_pv = 'Y'
                AND   h.year_month IN (
                    SELECT
                        year_month
                    FROM
                        pm_calendar
                    WHERE
                        year_month BETWEEN TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_avg_month),'YYYYMM') AND v_yyyymm
                )
            GROUP BY
                b.segment_com,
                b.segment_com2,
                b.project_color,
                h.region,
                h.sub_id
        ),seg_ww_pv AS ( -- SEG 평균 (WW) PV 
            SELECT
                'WW' type,
                MAX(year_month) year_month,
                b.segment_com,
                b.segment_com2,
                upper(b.project_color) AS project_color,
                h.region,
                h.sub_id,
                DECODE(SUM(DECODE(h.color_type,'Mono',h.set_qty,0) ),0,0, (SUM(h.mm_for_qty * b.after_toner) / SUM(DECODE(h.color_type,'Mono',h.set_qty
,0) ) ) ) seg_ww_pv,
                round(DECODE(SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ),0,0, (SUM(DECODE(h.color_type,'Color',DECODE(h.year_month
,v_yyyymm,h.mm_for_qty * h.after_toner,0),0) ) / SUM(DECODE(h.year_month,v_yyyymm,h.mm_for_qty * h.after_toner,0) ) ) ),6) seg_ww_color_mix
            FROM
                (
                    SELECT
                        year_month,
                        project_com,
                        region,
                        sub_id,
                        color_type,
                        set_qty,
                        mm_for_qty,
                        after_toner
                    FROM
                        pbfs_set_plc_print_hist a
                    WHERE
                        EXISTS (
                            SELECT
                                1
                            FROM
                                pbfs_set_plc_print_hist b
                            WHERE
                                a.project_com = b.project_com
                                AND   a.region = b.region
                                AND   a.sub_id = b.sub_id
                                AND   a.color_type = b.color_type
                                AND   b.year_month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_month2),'YYYYMM')
                        )
                ) h,
                (
                    SELECT
                        MAX(b.segment_com) AS segment_com,
                        nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type,
                        MAX(p.after_toner) AS after_toner,
                        MAX(p.include_pv) AS include_pv
                    FROM
                        pm_master_basic b,
                        pm_master_project_com p,
                        (
                            SELECT
                                comcd_desc AS segment,
                                comcd_nm AS segment_com2,
                                comncd AS project_com
                            FROM
                                pbfs_common_cd
                            WHERE
                                comncd_tcd = 'SEGMENT_GROUP'
                        ) s
                    WHERE
                        p.project_com = b.buffer1
                        AND   p.forecast_yn = 'Y'
                        AND   p.project_com = s.project_com (+)
                    GROUP BY
                        p.project_color,
                        p.project_com,
                        p.region,
                        p.sub_id,
                        p.color_type
                ) b
            WHERE
                1 = 1
                AND   h.project_com = b.project_com
                AND   h.region = b.region
                AND   h.sub_id = b.sub_id
                AND   h.color_type = b.color_type
                AND   h.region = 'ALL'
                AND   h.sub_id = 'ALL'
                AND   b.include_pv = 'Y'
                AND   h.year_month IN (
                    SELECT
                        year_month
                    FROM
                        pm_calendar
                    WHERE
                        year_month BETWEEN TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),v_avg_month),'YYYYMM') AND v_yyyymm
                )
            GROUP BY
                b.segment_com,
                b.segment_com2,
                b.project_color,
                h.region,
                h.sub_id
        ) SELECT
            a.year_month,
            v_year AS year,
            v_quater AS quater,
            a.segment_com,
            a.project_color,
            a.project_com,
            a.region,
            a.site,
            DECODE(a.target_flag,'Y',nvl(a.sub_pv,nvl(a.seg_sub_pv,nvl(a.seg_reg_pv,a.seg_ww_pv) ) ),nvl(a.seg_sub_pv,nvl(a.seg_reg_pv,a.seg_ww_pv
) ) ) set_plc_pv,
            nvl(a.seg_sub_pv,0),
            nvl(a.seg_reg_pv,0),
            nvl(a.seg_ww_pv,0),
            SYSDATE,
            'SYSTEM'
    --, NVL(A.AVG_DEV,0) AS AVG_DEV
           ,
            0 avg_dev,
            nvl(a.include_pv,'N') AS avg_yn,
            v_yyyymm AS base_yyyymm,
            nvl(a.sub_color_mix,nvl(a.seg_sub_color_mix,nvl(a.seg_reg_color_mix,a.seg_ww_color_mix) ) ) AS color_mix
          FROM
            (
                SELECT
                    w.year_month,
                    w.segment_com,
                    w.project_color,
                    w.project_com,
                    w.region,
                    w.site,
                    DECODE(p.sub_pv,0,NULL,p.sub_pv) AS sub_pv,
                    DECODE(sp.seg_sub_pv,0,NULL,sp.seg_sub_pv) AS seg_sub_pv,
                    DECODE(rp.seg_reg_pv,0,NULL,rp.seg_reg_pv) AS seg_reg_pv,
                    wp.seg_ww_pv
        -- , SP.AVG_DEV
                   ,
                    w.target_flag,
                    w.include_pv,
                    DECODE(p.sub_color_mix,0,NULL,p.sub_color_mix) AS sub_color_mix,
                    DECODE(sp.seg_sub_color_mix,0,NULL,sp.seg_sub_color_mix) AS seg_sub_color_mix,
                    DECODE(rp.seg_reg_color_mix,0,NULL,rp.seg_reg_color_mix) AS seg_reg_color_mix,
                    wp.seg_ww_color_mix
                FROM
                    w_param w,
                    sub_pv p,
                    seg_sub_pv sp,
                    seg_reg_pv rp,
                    seg_ww_pv wp
                WHERE
                    w.project_com = p.project_com (+)
                    AND   w.region = p.region (+)
                    AND   w.site = p.sub_id (+)
                    AND   w.project_color = p.project_color (+)
                    AND   w.segment_com = sp.segment_com2 (+)
                    AND   w.region = sp.region (+)
                    AND   w.site = sp.sub_id (+)
                    AND   w.project_color = sp.project_color (+)
                    AND   w.segment_com = rp.segment_com2 (+)
                    AND   w.region = rp.region (+)
                    AND   w.project_color = rp.project_color (+)
                    AND   w.segment_com = wp.segment_com2 (+)
                    AND   w.project_color = wp.project_color (+)
                    AND   w.project_color IS NOT NULL
            ) a
          WHERE
            NOT EXISTS (
                SELECT
                    1
                FROM
                    pbfs_master_set_pv p
                WHERE
                    p.year_month = a.year_month
                    AND   p.project_com = a.project_com
                    AND   p.region = a.region
                    AND   p.sub_id = a.site
                    AND   p.manual_yn = 'Y'
            )
                AND   DECODE(a.target_flag,'Y',nvl(a.sub_pv,nvl(a.seg_sub_pv,nvl(a.seg_reg_pv,a.seg_ww_pv) ) ),nvl(a.seg_sub_pv,nvl(a.seg_reg_pv,a.seg_ww_pv
) ) ) > 0;

    v_err_step := '200';
    UPDATE pbfs_master_set_pv
        SET
            set_plc_pv = least(set_plc_pv, (DECODE(sub_pv,0,DECODE(region_pv,0,ww_pv,region_pv),sub_pv) * 2.5) )
    WHERE
        year_month = v_year_month
        AND   DECODE(DECODE(sub_pv,0,DECODE(region_pv,0,ww_pv,region_pv),sub_pv),0,0,set_plc_pv / DECODE(sub_pv,0,DECODE(region_pv,0,ww_pv,region_pv
),sub_pv) ) >= 2.5
        AND   manual_yn = 'N';

    v_err_step := '300';          
      --  LBP/MFP 분배 로직 --
    FOR p_list IN (
        SELECT
            a.year_month,
            p.project_com,
            p.region,
            p.sub_id,
            p.set_plc_pv           
             --  , DECODE((A.L_SET_QTY + 1.2 * A.M_SET_QTY),0,0, (P.SET_PLC_PV * (A.L_SET_QTY + A.M_SET_QTY)) / (A.L_SET_QTY + 1.2 * A.M_SET_QTY))  AS LBP_PV
           ,
            DECODE( (a.l_set_qty1 + 1.2 * a.m_set_qty1),0,0, (p.set_plc_pv * (a.l_set_qty1 + a.m_set_qty1) ) / (a.l_set_qty1 + 1.2 * a.m_set_qty1) ) AS lbp_pv1,
            DECODE( (a.l_set_qty2 + 1.2 * a.m_set_qty2),0,0, (p.set_plc_pv * (a.l_set_qty2 + a.m_set_qty2) ) / (a.l_set_qty2 + 1.2 * a.m_set_qty2) ) AS lbp_pv2,
            DECODE(abs(l_set_qty1) + abs(m_set_qty1),0,DECODE( (a.l_set_qty2 + 1.2 * a.m_set_qty2),0,0, (p.set_plc_pv * (a.l_set_qty2 + a.m_set_qty2) ) / (a
.l_set_qty2 + 1.2 * a.m_set_qty2) ),DECODE( (a.l_set_qty1 + 1.2 * a.m_set_qty1),0,0, (p.set_plc_pv * (a.l_set_qty1 + a.m_set_qty1) ) / (a.l_set_qty1
+ 1.2 * a.m_set_qty1) ) ) AS lbp_pv
                                
              -- , DECODE((A.L_SET_QTY + 1.2 * A.M_SET_QTY),0,0, (1.2 * P.SET_PLC_PV * (A.L_SET_QTY + A.M_SET_QTY)) / (A.L_SET_QTY + 1.2 * A.M_SET_QTY))  AS MFP_PV                  
           ,
            DECODE( (a.l_set_qty1 + 1.2 * a.m_set_qty1),0,0, (1.2 * p.set_plc_pv * (a.l_set_qty1 + a.m_set_qty1) ) / (a.l_set_qty1 + 1.2 * a.m_set_qty1) ) AS mfp_pv1
,
            DECODE( (a.l_set_qty2 + 1.2 * a.m_set_qty2),0,0, (1.2 * p.set_plc_pv * (a.l_set_qty2 + a.m_set_qty2) ) / (a.l_set_qty2 + 1.2 * a.m_set_qty2) ) AS mfp_pv2
,
            DECODE(abs(l_set_qty1) + abs(m_set_qty1),0,DECODE( (a.l_set_qty2 + 1.2 * a.m_set_qty2),0,0, (1.2 * p.set_plc_pv * (a.l_set_qty2 + a.m_set_qty2)
) / (a.l_set_qty2 + 1.2 * a.m_set_qty2) ),DECODE( (a.l_set_qty1 + 1.2 * a.m_set_qty1),0,0, (1.2 * p.set_plc_pv * (a.l_set_qty1 + a.m_set_qty1) ) / (a.l_set_qty1
+ 1.2 * a.m_set_qty1) ) ) AS mfp_pv,
            a.acc_set_qty1,
            a.acc_set_qty2,
            DECODE(abs(l_set_qty1) + abs(m_set_qty1),0,a.acc_set_qty2,a.acc_set_qty1) AS acc_set_qty
        FROM
            (
         --       SELECT B.BUFFER1 AS PROJECT_COM, R.REGION, R.SITE, B.SEGMENT_COM AS SEGMENT
                SELECT
                    b.buffer1 AS project_com,
                    nvl(r.region,'ALL') AS region,
                    nvl(r.site,'ALL') AS site,
                    b.segment_com AS segment,
                    abs(round(SUM(DECODE(r.product,'C-LBP',r.s_qty,'M-LBP',r.s_qty,0) ),0) ) AS l_set_qty,
                    abs(round(SUM(
                        CASE
                            WHEN r.year
                            || r.month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),1),'YYYYMM') THEN DECODE(r.product,'C-LBP',r.s_qty,'M-LBP',r.s_qty,0)
                            ELSE 0
                        END
                    ),0) ) AS l_set_qty1,
                    abs(round(SUM(
                        CASE
                            WHEN r.year
                            || r.month > TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),1),'YYYYMM') THEN DECODE(r.product,'C-LBP',r.s_qty,'M-LBP',r.s_qty,0)
                            ELSE 0
                        END
                    ),0) ) AS l_set_qty2,
                    abs(round(SUM(DECODE(r.product,'C-MFP',r.s_qty,'L-MFP',r.s_qty,0) ),0) ) AS m_set_qty,
                    abs(round(SUM(
                        CASE
                            WHEN r.year
                            || r.month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),1),'YYYYMM') THEN DECODE(r.product,'C-MFP',r.s_qty,'L-MFP',r.s_qty,0)
                            ELSE 0
                        END
                    ),0) ) AS m_set_qty1,
                    abs(round(SUM(
                        CASE
                            WHEN r.year
                            || r.month > TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),1),'YYYYMM') THEN DECODE(r.product,'C-MFP',r.s_qty,'L-MFP',r.s_qty,0)
                            ELSE 0
                        END
                    ),0) ) AS m_set_qty2,
                    round(SUM(
                        CASE
                            WHEN r.year
                            || r.month <= TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),1),'YYYYMM') THEN r.s_qty
                            ELSE 0
                        END
                    ),0) AS acc_set_qty1,
                    round(SUM(
                        CASE
                            WHEN r.year
                            || r.month > TO_CHAR(add_months(TO_DATE(v_yyyymm,'YYYYMM'),1),'YYYYMM') THEN r.s_qty
                            ELSE 0
                        END
                    ),0) AS acc_set_qty2,
                    v_year_month AS year_month
                FROM
                    pbfs_sales_connect r,
                    pm_master_basic b
                WHERE
                    1 = 1
                    AND   r.supplies_type = 'SET'
                    AND   r.lab_office = 'S'
                    AND   r.product IN (
                        'C-LBP',
                        'C-MFP',
                        'M-LBP',
                        'L-MFP'
                    )
                    AND   r.segment IN (
                        'A3',
                        'SEG1',
                        'SEG2',
                        'SEG3',
                        'SEG4'
                    )
                    AND   r.year
                    || r.month <= TO_CHAR(add_months(TO_DATE(v_year_month,'YYYYMM'),1),'YYYYMM')
                    AND   r.basic = b.basic        
           --     GROUP BY B.BUFFER1, R.REGION, R.SITE, B.SEGMENT_COM
                GROUP BY
                    ROLLUP(b.buffer1,
                    r.region,
                    r.site),
                    b.segment_com
                HAVING
                    b.buffer1 IS NOT NULL
            ) a,
            pbfs_master_set_pv p
        WHERE
            a.year_month = p.year_month
            AND   a.project_com = p.project_com
            AND   a.region = p.region
            AND   a.site = p.sub_id
    ) LOOP
        BEGIN
            UPDATE pbfs_master_set_pv a
                SET
                    lbp_pv = round(p_list.lbp_pv,2),
                    mfp_pv = round(p_list.mfp_pv,2),
                    acc_set_qty = p_list.acc_set_qty
            WHERE
                a.year_month = p_list.year_month
                AND   a.project_com = p_list.project_com
                AND   a.region = p_list.region
                AND   a.sub_id = p_list.sub_id
                AND   a.set_plc_pv = p_list.set_plc_pv
                AND   a.manual_yn = 'N';
             --DBMS_OUTPUT.PUT_LINE('V_RESULT : '||V_RESULT);
             --DBMS_OUTPUT.PUT_LINE('PROJECT    = '||P_LIST.PROJECT);

        END;
    END LOOP;

    v_err_step := '400';
    UPDATE pbfs_master_set_pv
        SET
            set_plc_pv = round(set_plc_pv * 0.9,2),
            sub_pv = round(sub_pv * 0.9,2),
            region_pv = round(region_pv * 0.9,2),
            ww_pv = round(ww_pv * 0.9,2),
            lbp_pv = round(lbp_pv * 0.9,2),
            mfp_pv = round(mfp_pv * 0.9,2)
    WHERE
        year_month = v_year_month
        AND   manual_yn = 'N';

    v_err_step := '500';     

      /* --SET 누적수량이 0 인경우--
      LBP PageVolume = (LBP수량 + MFP수량) x PageVolume /(LPB수량 + 1.2 x MFP수량)
      MFP PageVolume = 1.2 x (LBP수량 + MFP수량) x PageVolume /(LPB수량 + 1.2 x MPF수량)
      */
    UPDATE pbfs_master_set_pv
        SET
            lbp_pv = ( 1 + 1 ) * set_plc_pv / ( 1 + 1.2 * 1 ),
            mfp_pv = 1.2 * ( 1 + 1 ) * set_plc_pv / ( 1 + 1.2 * 1 )
    WHERE
        (
            acc_set_qty = 0
            OR    acc_set_qty IS NULL
        )
        AND   manual_type IS NULL
        AND   year_month = v_year_month
        AND   manual_yn = 'N';

    v_err_step := '600';
    UPDATE pbfs_master_set_pv
        SET
            avg_ratio = round(DECODE(DECODE(sub_pv,0,DECODE(region_pv,0,ww_pv,region_pv),sub_pv),0,0,set_plc_pv / DECODE(sub_pv,0,DECODE(region_pv
,0,ww_pv,region_pv),sub_pv) ),2)
    WHERE
        year_month = v_year_month
        AND   manual_yn = 'N';

    v_err_step := '610';   

-- FIXED 인것을 UPDATE 한다 --
-- 출시 15개월 未 경과 모델중 NEW 인 것도 업데이트 대상에 포함
    FOR p_list IN (
        SELECT
            1 no,
            v_year_month AS year_month,
            s.region_cd,
            s.sub_id,
            m.project_com,
            m.lbp_pv_m,
            m.mfp_pv_m,
            m.color_mix_m,
            m.manual_type,
            m.pv_m
        FROM
            pbfs_master_set_pv_manual m,
            (
                SELECT
                    region_cd,
                    sub_id
                FROM
                    pbfs_master_subsidiary
                UNION ALL
                SELECT DISTINCT
                    region_cd,
                    'ALL'
                FROM
                    pbfs_master_subsidiary
                UNION ALL
                SELECT
                    'ALL',
                    'ALL'
                FROM
                    dual
            ) s
        WHERE
            1 = 1
            AND   m.region = 'ALL'
            AND   m.sub_id = 'ALL'
            AND   (
                m.manual_type = 'FIXED'
                OR    (
                    m.manual_type = 'NEW'
                    AND   months_between(TO_DATE(v_yyyymm,'YYYYMM'), (
                        SELECT
                            TO_DATE(MIN(c.year
                            || c.month),'YYYYMM')
                        FROM
                            pbfs_sales_connect c
                        WHERE
                            c.region = m.region
                            AND   c.site = m.sub_id
                            AND   c.project_com = m.project_com
                    ) ) < 15
                )
            )
        UNION ALL
        SELECT
            2 no,
            v_year_month AS year_month,
            s.region_cd,
            s.sub_id,
            m.project_com,
            m.lbp_pv_m,
            m.mfp_pv_m,
            m.color_mix_m,
            m.manual_type,
            m.pv_m
        FROM
            pbfs_master_set_pv_manual m,
            (
                SELECT
                    region_cd,
                    sub_id
                FROM
                    pbfs_master_subsidiary
                UNION ALL
                SELECT DISTINCT
                    region_cd,
                    'ALL'
                FROM
                    pbfs_master_subsidiary
            ) s
        WHERE
            1 = 1
            AND   m.region = s.region_cd
            AND   m.region != 'ALL'
            AND   m.sub_id = 'ALL'
            AND   (
                m.manual_type = 'FIXED'
                OR    (
                    m.manual_type = 'NEW'
                    AND   months_between(TO_DATE(v_yyyymm,'YYYYMM'), (
                        SELECT
                            TO_DATE(MIN(c.year
                            || c.month),'YYYYMM')
                        FROM
                            pbfs_sales_connect c
                        WHERE
                            c.region = m.region
                            AND   c.site = m.sub_id
                            AND   c.project_com = m.project_com
                    ) ) < 15
                )
            )
        UNION ALL
        SELECT
            3 no,
            v_year_month AS year_month,
            s.region_cd,
            s.sub_id,
            m.project_com,
            m.lbp_pv_m,
            m.mfp_pv_m,
            m.color_mix_m,
            m.manual_type,
            m.pv_m
        FROM
            pbfs_master_set_pv_manual m,
            (
                SELECT
                    region_cd,
                    sub_id
                FROM
                    pbfs_master_subsidiary
            ) s
        WHERE
            1 = 1
            AND   m.sub_id = s.sub_id
            AND   m.region != 'ALL'
            AND   m.sub_id != 'ALL'
            AND   (
                m.manual_type = 'FIXED'
                OR    (
                    m.manual_type = 'NEW'
                    AND   months_between(TO_DATE(v_yyyymm,'YYYYMM'), (
                        SELECT
                            TO_DATE(MIN(c.year
                            || c.month),'YYYYMM')
                        FROM
                            pbfs_sales_connect c
                        WHERE
                            c.region = m.region
                            AND   c.site = m.sub_id
                            AND   c.project_com = m.project_com
                    ) ) < 15
                )
            )
        ORDER BY
            1,
            3,
            4
    ) LOOP
        BEGIN
            UPDATE pbfs_master_set_pv a
                SET
                    lbp_pv_m = round(p_list.lbp_pv_m,2),
                    mfp_pv_m = round(p_list.mfp_pv_m,2),
                    color_mix_m = p_list.color_mix_m,
                    manual_type = p_list.manual_type,
                    set_plc_pv = p_list.pv_m
            WHERE
                a.year_month = p_list.year_month
                AND   a.project_com = p_list.project_com
                AND   a.region = p_list.region_cd
                AND   a.sub_id = p_list.sub_id
                AND   a.manual_yn = 'N';
             --DBMS_OUTPUT.PUT_LINE('V_RESULT : '||V_RESULT);
             --DBMS_OUTPUT.PUT_LINE('PROJECT    = '||P_LIST.PROJECT);

        END;
    END LOOP;              
  
      
-- NEW  인것을 INSERT 한다 --

    v_err_step := '700';  

    -- SUB --    
    INSERT INTO pbfs_master_set_pv (
        year_month,
        segment,
        project_color,
        project_com,
        region,
        sub_id,
        set_plc_pv,
        sub_pv,
        region_pv,
        ww_pv,
        update_date,
        create_date,
        create_user,
        quater,
        year,
        lbp_pv,
        mfp_pv,
        avg_ratio,
        manual_yn,
        avg_dev,
        avg_yn,
        acc_set_qty,
        base_yyyymm,
        color_mix,
        lbp_pv_m,
        mfp_pv_m,
        color_mix_m,
        manual_type
    )
        SELECT
            a.year_month,
            a.segment_com2,
            upper(a.project_color),
            a.project_com,
            a.region_cd,
            a.sub_id_cd,
            a.pv_m AS set_plc_pv,
            0 sub_pv,
            0 region_pv,
            0 ww_pv,
            SYSDATE update_date,
            SYSDATE create_date,
            'ADMIN' create_user,
            v_quater quater,
            v_year year,
            a.lbp_pv_m AS lbp_pv,
            a.mfp_pv_m AS mfp_pv,
            0 avg_ratio,
            'N' manual_yn,
            0 avg_dev,
            '' avg_yn,
            0 acc_set_qty,
            v_yyyymm base_yyyymm,
            a.color_mix_m color_mix,
            a.lbp_pv_m,
            a.mfp_pv_m,
            a.color_mix_m,
            a.manual_type
        FROM
            (
                SELECT
                    v_year_month year_month,
                    p.segment_com2,
                    p.project_color,
                    s.region_cd,
                    s.sub_id AS sub_id_cd,
                    m.project_com,
                    m.region,
                    m.sub_id,
                    m.lbp_pv_m,
                    m.mfp_pv_m,
                    m.color_mix_m,
                    m.manual_type,
                    m.pv_m
                FROM
                    pbfs_master_set_pv_manual m,
                    (
                        SELECT
                            region_cd,
                            sub_id
                        FROM
                            pbfs_master_subsidiary
                    ) s,
                    (
                        SELECT
                            MAX(b.segment_com) AS segment_com,
                            nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                            p.project_color,
                            p.project_com,
                            p.region,
                            p.sub_id,
                            MAX(p.after_toner) AS after_toner,
                            MAX(p.include_pv) AS include_pv
                        FROM
                            pm_master_basic b,
                            pm_master_project_com p,
                            (
                                SELECT
                                    comcd_desc AS segment,
                                    comcd_nm AS segment_com2,
                                    comncd AS project_com
                                FROM
                                    pbfs_common_cd
                                WHERE
                                    comncd_tcd = 'SEGMENT_GROUP'
                            ) s
                        WHERE
                            p.project_com = b.buffer1
                            AND   p.forecast_yn = 'Y'
                            AND   p.project_com = s.project_com (+)
                        GROUP BY
                            p.project_color,
                            p.project_com,
                            p.region,
                            p.sub_id
                    ) p
                WHERE
                    1 = 1
                    AND   m.project_com = p.project_com (+)
                    AND   m.region = p.region (+)
                    AND   m.sub_id = p.sub_id (+)
                    AND   m.sub_id = s.sub_id
                    AND   m.region != 'ALL'
                    AND   m.sub_id != 'ALL'
            ) a
        WHERE
            NOT EXISTS (
                SELECT
                    1
                FROM
                    pbfs_master_set_pv p
                WHERE
                    p.project_com = a.project_com
                    AND   p.region = a.region_cd
                    AND   p.sub_id = a.sub_id_cd
                    AND   p.year_month = a.year_month
            );

    v_err_step := '800';  

    -- REGION --    
    INSERT INTO pbfs_master_set_pv (
        year_month,
        segment,
        project_color,
        project_com,
        region,
        sub_id,
        set_plc_pv,
        sub_pv,
        region_pv,
        ww_pv,
        update_date,
        create_date,
        create_user,
        quater,
        year,
        lbp_pv,
        mfp_pv,
        avg_ratio,
        manual_yn,
        avg_dev,
        avg_yn,
        acc_set_qty,
        base_yyyymm,
        color_mix,
        lbp_pv_m,
        mfp_pv_m,
        color_mix_m,
        manual_type
    )
        SELECT
            a.year_month,
            a.segment_com2,
            upper(a.project_color),
            a.project_com,
            a.region_cd,
            a.sub_id_cd,
            a.pv_m AS set_plc_pv,
            0 sub_pv,
            0 region_pv,
            0 ww_pv,
            SYSDATE update_date,
            SYSDATE create_date,
            'ADMIN' create_user,
            v_quater quater,
            v_year year,
            a.lbp_pv_m AS lbp_pv,
            a.mfp_pv_m AS mfp_pv,
            0 avg_ratio,
            'N' manual_yn,
            0 avg_dev,
            '' avg_yn,
            0 acc_set_qty,
            v_yyyymm base_yyyymm,
            a.color_mix_m color_mix,
            a.lbp_pv_m,
            a.mfp_pv_m,
            a.color_mix_m,
            a.manual_type
        FROM
            (
                SELECT
                    v_year_month year_month,
                    p.segment_com2,
                    p.project_color,
                    s.region_cd,
                    s.sub_id AS sub_id_cd,
                    m.project_com,
                    m.region,
                    m.sub_id,
                    m.lbp_pv_m,
                    m.mfp_pv_m,
                    m.color_mix_m,
                    m.manual_type,
                    m.pv_m
                FROM
                    pbfs_master_set_pv_manual m,
                    (
                        SELECT
                            region_cd,
                            sub_id
                        FROM
                            pbfs_master_subsidiary
                        UNION ALL
                        SELECT DISTINCT
                            region_cd,
                            'ALL'
                        FROM
                            pbfs_master_subsidiary
                    ) s,
                    (
                        SELECT
                            MAX(b.segment_com) AS segment_com,
                            nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                            p.project_color,
                            p.project_com,
                            p.region,
                            p.sub_id,
                            MAX(p.after_toner) AS after_toner,
                            MAX(p.include_pv) AS include_pv
                        FROM
                            pm_master_basic b,
                            pm_master_project_com p,
                            (
                                SELECT
                                    comcd_desc AS segment,
                                    comcd_nm AS segment_com2,
                                    comncd AS project_com
                                FROM
                                    pbfs_common_cd
                                WHERE
                                    comncd_tcd = 'SEGMENT_GROUP'
                            ) s
                        WHERE
                            p.project_com = b.buffer1
                            AND   p.forecast_yn = 'Y'
                            AND   p.project_com = s.project_com (+)
                        GROUP BY
                            p.project_color,
                            p.project_com,
                            p.region,
                            p.sub_id
                    ) p
                WHERE
                    1 = 1
                    AND   m.project_com = p.project_com (+)
                    AND   m.region = p.region (+)
                    AND   m.sub_id = p.sub_id (+)
                    AND   m.region = s.region_cd
                    AND   m.region != 'ALL'
                    AND   m.sub_id = 'ALL'
            ) a
        WHERE
            NOT EXISTS (
                SELECT
                    1
                FROM
                    pbfs_master_set_pv p
                WHERE
                    p.project_com = a.project_com
                    AND   p.region = a.region_cd
                    AND   p.sub_id = a.sub_id_cd
                    AND   p.year_month = a.year_month
            );

    v_err_step := '900';  

    -- WW --    
    INSERT INTO pbfs_master_set_pv (
        year_month,
        segment,
        project_color,
        project_com,
        region,
        sub_id,
        set_plc_pv,
        sub_pv,
        region_pv,
        ww_pv,
        update_date,
        create_date,
        create_user,
        quater,
        year,
        lbp_pv,
        mfp_pv,
        avg_ratio,
        manual_yn,
        avg_dev,
        avg_yn,
        acc_set_qty,
        base_yyyymm,
        color_mix,
        lbp_pv_m,
        mfp_pv_m,
        color_mix_m,
        manual_type
    )
        SELECT
            a.year_month,
            a.segment_com2,
            upper(a.project_color),
            a.project_com,
            a.region_cd,
            a.sub_id_cd,
            a.pv_m AS set_plc_pv,
            0 sub_pv,
            0 region_pv,
            0 ww_pv,
            SYSDATE update_date,
            SYSDATE create_date,
            'ADMIN' create_user,
            v_quater quater,
            v_year year,
            a.lbp_pv_m AS lbp_pv,
            a.mfp_pv_m AS mfp_pv,
            0 avg_ratio,
            'N' manual_yn,
            0 avg_dev,
            '' avg_yn,
            0 acc_set_qty,
            v_yyyymm base_yyyymm,
            a.color_mix_m color_mix,
            a.lbp_pv_m,
            a.mfp_pv_m,
            a.color_mix_m,
            a.manual_type
        FROM
            (
                SELECT
                    v_year_month year_month,
                    p.segment_com2,
                    p.project_color,
                    s.region_cd,
                    s.sub_id AS sub_id_cd,
                    m.project_com,
                    m.region,
                    m.sub_id,
                    m.lbp_pv_m,
                    m.mfp_pv_m,
                    m.color_mix_m,
                    m.manual_type,
                    m.pv_m
                FROM
                    pbfs_master_set_pv_manual m,
                    (
                        SELECT
                            region_cd,
                            sub_id
                        FROM
                            pbfs_master_subsidiary
                        UNION ALL
                        SELECT DISTINCT
                            region_cd,
                            'ALL'
                        FROM
                            pbfs_master_subsidiary
                        UNION ALL
                        SELECT
                            'ALL',
                            'ALL'
                        FROM
                            dual
                    ) s,
                    (
                        SELECT
                            MAX(b.segment_com) AS segment_com,
                            nvl(MAX(s.segment_com2),MAX(b.segment_com) ) AS segment_com2,
                            p.project_color,
                            p.project_com,
                            p.region,
                            p.sub_id,
                            MAX(p.after_toner) AS after_toner,
                            MAX(p.include_pv) AS include_pv
                        FROM
                            pm_master_basic b,
                            pm_master_project_com p,
                            (
                                SELECT
                                    comcd_desc AS segment,
                                    comcd_nm AS segment_com2,
                                    comncd AS project_com
                                FROM
                                    pbfs_common_cd
                                WHERE
                                    comncd_tcd = 'SEGMENT_GROUP'
                            ) s
                        WHERE
                            p.project_com = b.buffer1
                            AND   p.forecast_yn = 'Y'
                            AND   p.project_com = s.project_com (+)
                        GROUP BY
                            p.project_color,
                            p.project_com,
                            p.region,
                            p.sub_id
                    ) p
                WHERE
                    1 = 1
                    AND   m.project_com = p.project_com (+)
                    AND   m.region = p.region (+)
                    AND   m.sub_id = p.sub_id (+)
                    AND   m.region = 'ALL'
                    AND   m.sub_id = 'ALL'
            ) a
        WHERE
            NOT EXISTS (
                SELECT
                    1
                FROM
                    pbfs_master_set_pv p
                WHERE
                    p.project_com = a.project_com
                    AND   p.region = a.region_cd
                    AND   p.sub_id = a.sub_id_cd
                    AND   p.year_month = a.year_month
            );

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        v_error_code := sqlcode;
        v_error_msg := sqlerrm
        || 'V_ERR_STEP : '
        || v_err_step; --|| ' : ' ||  V_SUB_ID|| ' : ' ||  V_COLOR_TYPE  ;
        v_user := user;
        
      --  RESULT := '0';
        dbms_output.put_line(TO_CHAR(v_error_code)
        || '-'
        || v_error_msg);
        INSERT INTO error_log (
            code,
            object_name,
            message,
            create_user,
            create_date
        ) VALUES (
            v_error_code,
            'P_PBFS_MASTER_PV_AVG',
            v_error_msg,
            v_user,
            SYSDATE
        );

        COMMIT;
END;