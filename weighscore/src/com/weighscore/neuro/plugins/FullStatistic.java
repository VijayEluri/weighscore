package com.weighscore.neuro.plugins;

import com.weighscore.neuro.*;

/**
 * <p>Title: MetNeuroScore</p>
 *
 * <p>Description: Neural scoring subsystem</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Vsetech</p>
 *
 * @author Fedd Kraft
 * @version 0.1
 */
public class FullStatistic extends Statistic {
    // ���������� ��������
    public long askCnt=0;// ���������� ��������, ��������� ����� ������ ��� ������
    public double askAvg=0;// ������� �������� �������
    public double askAvgPosDev=0;// ������� ������������� ������� ����� ������� ��������� � ����������� ����������
    public double askAvgNegDev=0;// ������� ������������� ������� ����� ������� ��������� � ����������� ����������
    public long askPosDevCnt=0;// ���������� ������������� ����������

    // ���������� ������ ��� ��������
    public long errCnt=0;// ���������� �������� (� ��� ����� � ����������� ����������)
    public double errAvg=0;// ������� �������� ������ (������� ������� ����� ������ � ���������� ��������)
    public double errAvgPosDev=0; // ������� ������������� ������� ����� ������� ������� � ����� ��������
    public long errPosDevCnt=0;// ���������� ������������� ����������
    public double errAvgNegDev=0; // ������� ������������� ������� ����� ������� ������� � ����� ��������

    public synchronized void recordError(double error){

        // ������������ ������ � ���������� ��������� ������
        //super.recordError(error);

        // ������������ ��������� ����������
        // �������� ������� �������
        this.errAvg = (this.errAvg * this.errCnt + error) /
                                (this.errCnt + 1);
        //������� ������� ����������
        double dev = this.errAvg - error;
        if (dev > 0) {
            // �������������
            this.errAvgPosDev =
                    (this.errAvgPosDev * this.errPosDevCnt + dev) /
                                          (this.errPosDevCnt + 1);

            this.errPosDevCnt++;
        } else {
            // �������������
            double errNegDevCnt = this.errCnt -
                                  this.errPosDevCnt;

            this.errAvgNegDev = (this.errAvgNegDev *
                                           errNegDevCnt + dev) /
                                          (errNegDevCnt + 1);
        }
        this.errCnt++;
    }




    public synchronized void recordAsking(double question){
        //log.trace("start record asking statistic");
        // �������� ������� ������
        this.askAvg = (this.askAvg * this.askCnt + question) / (this.askCnt + 1);
        //������� ������� ����������
        double dev = this.askAvg - question;
        if(dev>0){
            // �������������
            this.askAvgPosDev = (this.askAvgPosDev * this.askPosDevCnt + dev) / (this.askPosDevCnt + 1);

            this.askPosDevCnt++;
        } else {
            double askNegDevCnt = this.askCnt-this.askPosDevCnt;
            // �������������
            this.askAvgNegDev = (this.askAvgNegDev * askNegDevCnt + dev) / (askNegDevCnt + 1);
        }
        this.askCnt++;
    }

    public void recordTeaching(double correction) {
    }
}
